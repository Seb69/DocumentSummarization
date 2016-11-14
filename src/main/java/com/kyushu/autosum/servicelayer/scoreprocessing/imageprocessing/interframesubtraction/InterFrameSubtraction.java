package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction;

import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.misc.GPixelMath;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.gui.image.ShowImages;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.GrayU8;
import com.google.common.primitives.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author ANDRE
 * @since 30/05/16
 */
@Service
public class InterFrameSubtraction {

    private static final Logger LOG = LoggerFactory.getLogger(InterFrameSubtraction.class);

    private GrayF32 imageDifference;

    private GrayU8 imageBinary;

    public  Long calculationInterFrameSubtraction(BufferedImage bufferedImage1, BufferedImage bufferedImage2) {

        // convert into GrayScale
        GrayF32 input1 = ConvertBufferedImage.convertFromSingle(bufferedImage1, null, GrayF32.class);
        GrayF32 input2 = ConvertBufferedImage.convertFromSingle(bufferedImage2, null, GrayF32.class);

        // Calculate difference B - A
        calculateDifference(input1, input2);

        // Apply Threshold
        applyThreshold(imageDifference,15);

        long whiteBytes = calculateDifference(imageBinary);

        // Display result
//        displayImage(imageBinary);

        return Long.valueOf(whiteBytes);
    }

    /**
     * Calculate the difference between two image (GrayF32 image only)
     * imageB - imaegA
     * @param imageA  to compare
     * @param imageB to compare
     */
    void calculateDifference(GrayF32 imageA, GrayF32 imageB) {
        GrayF32 diff2 = new GrayF32(imageA.getWidth(), imageA.getHeight());
        GPixelMath.diffAbs(imageA, imageB, diff2);
        this.imageDifference = diff2;
    }

    /**
     * Apply a threshold on the image result. Every pixel under thresholdLevel is replace by
     * black, or white either
     * @param imageDifference
     * @param thresholdLevel
     */
    void applyThreshold(GrayF32 imageDifference, int thresholdLevel) {
        GrayU8 binary2 = new GrayU8(imageDifference.width, imageDifference.height);
        GThresholdImageOps.threshold(imageDifference, binary2, thresholdLevel, true);
        this.imageBinary = binary2;

    }

    /**
     * Calculate the number of white pixels
     * @param imageBinary the calculate
     * @return the number of white pixels
     */
    long calculateDifference(GrayU8 imageBinary) {
        // Retrieve binary array
        byte[] byteArray = imageBinary.getData();

        // Convert array into list
        List<Byte> byteList = Bytes.asList(byteArray);

        // Calculate the number of white bytes
        return byteList.stream()
                .filter(b -> b == 0)
                .count();
    }

    /**
     *
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
