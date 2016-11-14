package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.saliency;

import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.gui.image.ShowImages;
import boofcv.struct.image.GrayU8;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.threshold.AdaptiveLocalThresholdGaussian;
import org.openimaj.image.saliency.AchantaSaliency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * @author ANDRE
 * @since 05/07/16
 */
@Service
@SuppressWarnings("Duplicates")
public class SaliencyProcessingImpl implements SaliencyProcessing {

    private static final Logger LOG = LoggerFactory.getLogger(SaliencyProcessingImpl.class);

    private GrayU8 imageBinary;

    @Override
    public void SaliencyProcessing(File inputFile) throws IOException, InterruptedException {

//        MBFImage image = ImageUtilities.readMBF(inputFile);
//
//        AchantaSaliency achantaSaliency = new AchantaSaliency();
//
//        achantaSaliency.analyseImage(image);
//        FImage fiImage = achantaSaliency.getSaliencyMap();
//
//        // Create bufferedImage
//        BufferedImage bufferedImage = ImageUtilities.createBufferedImage(fiImage);
//
//        DisplayUtilities.display(bufferedImage,"buffered Image");

        // convert into GrayScale
//        GrayU8 imageGray = ConvertBufferedImage.extractGrayU8(bufferedImage);
//        displayImage(imageGray);
//        applyThreshold(imageGray, 100);


        MBFImage image = ImageUtilities.readMBF(inputFile);

        AchantaSaliency achantaSaliency = new AchantaSaliency();

        achantaSaliency.analyseImage(image);
        FImage fiImage = achantaSaliency.getSaliencyMap();

        DisplayUtilities.display(fiImage.toRGB(), "Achanta Saliency ");

        AdaptiveLocalThresholdGaussian otsuThreshold = new AdaptiveLocalThresholdGaussian(500f, 6f);
        otsuThreshold.processImage(fiImage);

//        displayImage(binary2);


        Thread.sleep((long) 10000);

    }


    /**
     * Apply a threshold on the image result. Every pixel under thresholdLevel is replace by
     * black, or white either
     *
     * @param imageDifference
     * @param thresholdLevel
     */
    public void applyThreshold(GrayU8 imageDifference, int thresholdLevel) {
        GrayU8 binary2 = new GrayU8(imageDifference.width, imageDifference.height);
        GThresholdImageOps.threshold(imageDifference, binary2, thresholdLevel, true);
        this.imageBinary = binary2;

    }

    /**
     * @param imageBinary to display
     */
    void displayImage(GrayU8 imageBinary) {

        BufferedImage imageOut = new BufferedImage(imageBinary.width, imageBinary.height, BufferedImage.TYPE_BYTE_BINARY);

        VisualizeBinaryData.renderBinary(imageBinary, true, imageOut);

        // Display multiple images in the same window
        ListDisplayPanel gui = new ListDisplayPanel();
        gui.addImage(imageOut, "Image interFrame");

        ShowImages.showWindow(gui, "InterFrame Result");
    }

}
