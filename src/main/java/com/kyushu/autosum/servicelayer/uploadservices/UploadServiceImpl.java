package com.kyushu.autosum.servicelayer.uploadservices;


import com.kyushu.autosum.servicelayer.uploadservices.exceptions.FileNotUploaded;
import com.kyushu.autosum.servicelayer.uploadservices.util.CreateFileToUpload;
import com.kyushu.autosum.servicelayer.util.objecthandler.inout.InStream;
import com.kyushu.autosum.servicelayer.util.objecthandler.inout.OutStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class which upload file to server side
 *
 * @author ANDRE
 * @date 10/05/16
 */
@Service
public class UploadServiceImpl implements UploadService {

    private static final Logger LOG = LoggerFactory.getLogger(UploadServiceImpl.class);

    /**
     * Upload any file
     *
     * @param multipartFile file to upload
     */
    @Override
    public File uploadFile(MultipartFile multipartFile) {

        // Create fileDest (server location)
        File fileUpload = CreateFileToUpload.createFileToUpload(multipartFile.getOriginalFilename());

        InputStream inputStream = InStream.createInputStream(multipartFile);
        OutputStream outputStream = OutStream.createFileOutputStream(fileUpload);

        copy(inputStream, outputStream);

        return fileUpload;
    }

    /**
     * Handle IOException fot copy method of FileCopyUtils
     *
     * @param inputStream  original file
     * @param outputStream destination file
     */
    void copy(InputStream inputStream, OutputStream outputStream) {

        try {
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new FileNotUploaded(e);
        }
    }

}
