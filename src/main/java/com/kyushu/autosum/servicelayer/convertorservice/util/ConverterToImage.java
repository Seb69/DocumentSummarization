package com.kyushu.autosum.servicelayer.convertorservice.util;

import com.kyushu.autosum.servicelayer.convertorservice.exceptions.FailedToConvert;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allow to convert from PDF to Image JPEG
 * @author ANDRE
 * @since 25/05/16
 */
public class ConverterToImage {

    private static final Logger LOG = LoggerFactory.getLogger(ConverterToImage.class);

    public static List<BufferedImage> convertToImage(File file) {

        PDDocument document = load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        List<BufferedImage> bufferedImageList = new ArrayList<>();

        int pageCounter = 0;
        for (PDPage page : document.getPages())
        {
            bufferedImageList.add(renderImageWithDPI(pdfRenderer, pageCounter));
            pageCounter++;
        }
        close(document);

        return bufferedImageList;
    }

    /**
     * Handle IOException
     * @param file
     * @return
     */
    static PDDocument load(File file) {
        PDDocument pdDocument;
        try {
            pdDocument = PDDocument.load(file);
        } catch (IOException e) {
            throw new FailedToConvert(e);
        }
        return pdDocument;
    }

    /**
     * Handle IOException
     * @param pdDocument created
     */
    static void close(PDDocument pdDocument) {
        try {
            pdDocument.close();
        } catch (IOException e) {
            LOG.error(e.toString());
        }
    }

    /**
     * Handle IOException
     * @param pdfRenderer  need to access render method
     * @param pageCounter page number of document
     * @return bufferimage
     */
    static BufferedImage renderImageWithDPI(PDFRenderer pdfRenderer,int pageCounter ) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);
        } catch (IOException e) {
            throw new FailedToConvert("Error during conversion ", e);
        }
        return bufferedImage;
    }
}
