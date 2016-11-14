package com.kyushu.autosum.servicelayer.convertorservice;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.kyushu.autosum.servicelayer.convertorservice.exceptions.FailedToConvert;
import com.kyushu.autosum.servicelayer.convertorservice.util.GoogleDriveAuth;
import com.kyushu.autosum.servicelayer.util.objecthandler.inout.OutStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

/**
 * This class is using Google Drive API
 * We upload the file (ppt,pptx) into Google server and retrieve it in pdf format
 */
@Service
public class PowerpointToPDFImpl implements PowerpointToPDF {

    private static final Logger LOG = LoggerFactory.getLogger(PowerpointToPDFImpl.class);

    private static Drive service = GoogleDriveAuth.getDriveService();

    private static String folderID = null;

    /**
     * Convert PPT, PPTX to PDF
     * @param file to convert
     * @return the pdf file
     */
    public java.io.File convertToPDF(java.io.File file)  {

        checkWorkspaceFolder();

        File uploadedFile = uploadFile(file);

        java.io.File newFile = downloadFile(file, uploadedFile);

        deleteFile(uploadedFile.getId());

        return newFile;
    }

    /**
     * Upload file into google drive into ppt or pptx mimeType
     * @param file to upload
     * @return the file uploaded
     */
    File uploadFile(java.io.File file) {

        File fileMetadata = new File();
        fileMetadata.setName("TemporaryFile");
        fileMetadata.setParents(Collections.singletonList(folderID));
        fileMetadata.setMimeType("application/vnd.google-apps.presentations");

        FileContent mediaContent = new FileContent("application/vnd.ms-powerpoint", file);

        return upload(fileMetadata,mediaContent);

    }


    /**
     * Download the file from google drive in PDF format, mimeType: "application/pdf"
     * @param OriginalFile before uploaded
     * @param uploadedFile after upload
     * @return the file downloaded
     */
    java.io.File downloadFile(java.io.File OriginalFile, File uploadedFile) {

        java.io.File folder = new java.io.File(OriginalFile.getParent().concat("/PDF_Slides"));

        folder.mkdir();

        String folderPath = folder.getPath();

        String fileId = uploadedFile.getId();
        java.io.File newFile = new java.io.File(folderPath.concat("/PDF.pdf"));
        OutputStream outputStream = OutStream.createFileOutputStream(newFile);

        exportFile(fileId, "application/pdf", outputStream);

        return newFile;

    }

    /**
     * Check if workspace is present
     * Create new folder if there is no folder
     */
    void checkWorkspaceFolder() {

        // Check if folder exist
        searchWorkspaceFolder();

        // Create folder if it does not exist yet
        if (folderID == null) createFolder();

    }

    /**
     * Search the workspace
     */
    void searchWorkspaceFolder() {

        String pageToken = null;
        FileList fileList;
        do {
            fileList = getList(pageToken);
            for (File file : fileList.getFiles()) {
                LOG.debug("Found file: "+  file.getName() + " " + file.getId() + "\n");
                if (file.getName().equals("AutoSum_Workspace")) folderID = file.getId();
            }
            pageToken = fileList.getNextPageToken();
        } while (pageToken != null);
    }

    /**
     * Handle IOException and execute search file
     *
     * @return the Drive Files List
     */
    FileList getList(String pageToken) {
        FileList fileList = null;
        try {
            fileList = service.files().list()
                    .setQ("mimeType='application/vnd.google-apps.folder' and trashed = false")
                    .setSpaces("drive")
                    .setFields("nextPageToken, files(id, name)")
                    .setPageToken(pageToken)
                    .execute();
        } catch (IOException e) {
            LOG.error(e.toString());
        }
        return fileList;
    }


    void createFolder() {

        File fileMetadata = new File();
        fileMetadata.setName("AutoSum_Workspace");
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        File file = upload(fileMetadata);

        folderID = file.getId();

    }

    /**
     * Handle IOExceotion
     *
     * @param id   the file id
     * @param mime the mime type
     */
    void exportFile(String id, String mime, OutputStream outputStream) {

        try {
            service.files().export(id, mime).executeMediaAndDownloadTo(outputStream);
        } catch (IOException e) {
            throw new FailedToConvert("Error during PPT conversion", e);
        }
    }

    /**
     * Handle IOException
     *
     * @param fileMetadata to create
     * @param mediaContent the type
     * @return the file created
     */
    File upload(File fileMetadata, FileContent mediaContent) {

        File file;
        try {
            file = service.files().create(fileMetadata, mediaContent)
                    .execute();
        } catch (IOException e) {
            throw new FailedToConvert("Failed during PPT conversion",e);
        }
        return file;

    }

    /**
     * Handle IOException
     *
     * @param fileMetadata to create
     * @return the file created
     */
    File upload(File fileMetadata) {

        File file;
        try {
            file = service.files().create(fileMetadata)
                    .execute();
        } catch (IOException e) {
            throw new FailedToConvert("Failed during PPT conversion",e);
        }
        return file;

    }

    /**
     * Handle IOException
     *
     * @param fileId to delete
     */
    void deleteFile(String fileId) {
        try {
            service.files().delete(fileId).execute();
        } catch (IOException e) {
            throw new FailedToConvert("Failed during PPT conversion",e);
        }

    }


}
