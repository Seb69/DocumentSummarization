package com.kyushu.autosum.servicelayer.convertorservice.util;

import com.kyushu.autosum.servicelayer.convertorservice.PowerpointToPDFImpl;
import com.kyushu.autosum.servicelayer.convertorservice.exceptions.FailedToConvert;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * @author ANDRE
 * @since 25/05/16
 */
public class PDFSplitter {

    private static final Logger LOG = LoggerFactory.getLogger(PowerpointToPDFImpl.class);

    /**
     * Method which split a pdf into one slide pdf
     * The name of the pdf created is 0.pdf 1.pdf ...
     *
     * @param file to split
     * @throws IOException
     */
    public static void splitPDF(File file) {

        PDDocument document = load(file);

        List<PDDocument> aParts = split(document);

        LOG.debug("PDF slides created: ".concat(String.valueOf(aParts.size())));

        for (int i = 0; i < aParts.size(); i++) {

            File slide = new File(createPath(file).concat("/").concat(String.valueOf(i)).concat(".pdf"));
            save(aParts.get(i), slide);
        }
    }

    /**
     * Retrieve to path where to create the single pdf file
     * In case of PDF_Slides not exist: create the folder
     * @param file to check
     * @return the path where to create file
     */
    static String createPath(File file) {
        String path;
        // Test if the original file is a PDF or not
        // If file is a PPT or PPTX, so PDF_Slides already exist
        if (file.getParentFile().getName().equals("PDF_slides"))
            return file.getParent();
        else {
            // Create folder in case of PDF_Slides does not exist
            File folder = new java.io.File(file.getParent().concat("/PDF_slides"));
            folder.mkdir();

            return file.getParent().concat("/PDF_slides");
        }

    }


    /**
     * Handle IOException
     *
     * @param file to convert into PDDocument
     * @return PDDocument created
     */
    static PDDocument load(File file) {
        PDDocument document;
        try {
            document = PDDocument.load(file);
        } catch (IOException e) {
            throw new FailedToConvert(e);
        }
        return document;

    }


    static List<PDDocument> split(PDDocument document) {
        List<PDDocument> pdDocumentList = Collections.EMPTY_LIST;

        Splitter aSplitter = new Splitter();
        try {
            pdDocumentList = aSplitter.split(document);
        } catch (IOException e) {
            throw new FailedToConvert(e);
        }
        return pdDocumentList;
    }

    static void save(PDDocument pdDocument, File file) {
        try {
            pdDocument.save(file);
        } catch (IOException e) {
            throw new FailedToConvert(e);
        }
    }

}
