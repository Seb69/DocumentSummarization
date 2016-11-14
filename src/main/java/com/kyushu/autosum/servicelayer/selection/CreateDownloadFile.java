package com.kyushu.autosum.servicelayer.selection;

import com.kyushu.autosum.Application;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.servicelayer.util.objecthandler.file.FileHandler;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * @author ANDRE
 * @since 17/06/16
 */
@Service
public class CreateDownloadFile {

    private static final Logger LOG = LoggerFactory.getLogger(CreateDownloadFile.class);

    private PDFMergerUtility mergePdf;

    private String idNumber;

    /**
     * This method merge the selected slide into a single file (PDF)
     * @param material to process selection
     * @return the inputStreamResource
     */
    File createDownloadFile(Material material) {

        mergePdf = new PDFMergerUtility();
        mergePdf.setDestinationFileName(getFileMergeDestination(material));

        ClassPathResource slideFile = new ClassPathResource(Application.UploadDir.concat("/").concat(idNumber).concat("/PDF_Slides/"));

        List<Slide> slideList = material.getSlideList();

        OptionalInt startingID = material.getSlideList().stream()
                .mapToInt(Slide::getSLIDE_ID)
                .min();


        // Reorder slide in the right way
        Comparator<Slide> bySlideWeight = (s1, s2) -> Double.compare(s1.getSLIDE_ID(), s2.getSLIDE_ID());

        List<Slide> slideListOrdered = material.getSlideList().stream()
                .sorted(bySlideWeight)
                .collect(Collectors.toList());


        for (int i = 0; i < slideListOrdered.size(); i++) {
            if (slideListOrdered.get(i).getSelected()) {
                LOG.debug("Slide merged: "+ slideListOrdered.get(i).getSLIDE_ID());
                LOG.debug("File number: " + (slideListOrdered.get(i).getSLIDE_ID()-startingID.getAsInt()));
                File file = new File(slideFile.getPath().concat(String.valueOf(slideListOrdered.get(i).getSLIDE_ID()-startingID.getAsInt())).concat(".pdf"));
                addSource(file);
            }
        }

        mergeDocuments();

        File file = new File(mergePdf.getDestinationFileName());
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOG.error(e.toString());
        }

        return file;
    }

    /**
     * This method allow to retrieve the file merge destination
     * We have chosen to create file in Random Id folder, Ex /4vm7/MERGE_FILE.pdf
     * @param material to focus
     * @return the right path
     */
    String getFileMergeDestination(Material material) {

        // Retrieve the random id number
        String materialPath[] = material.getMaterialFilePATH().split("/");
        idNumber = materialPath[materialPath.length - 2];

        // Get the right folder where every file are
        String PATH = Application.UploadDir.concat("/").concat(idNumber);

        return PATH.concat("/MERGE_FILE.pdf");
    }

    /**
     * Handle FileNotFoundException
     * @param file to merge into the merged file
     */
    void addSource(File file) {

        try {
            mergePdf.addSource(file);
        } catch (FileNotFoundException e) {
            LOG.error(e.toString());
        }
    }

    /**
     * Handle IOException
     */
    void mergeDocuments() {
        try {
            mergePdf.mergeDocuments();
        } catch (IOException e) {
            LOG.error(e.toString());
        }
    }

}
