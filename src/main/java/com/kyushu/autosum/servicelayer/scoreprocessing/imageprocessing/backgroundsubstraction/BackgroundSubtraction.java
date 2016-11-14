package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction;

import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.misc.GPixelMath;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.gui.image.ShowImages;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.GrayU8;
import com.google.common.primitives.Bytes;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


/**
 * @author ANDRE
 * @since 20/05/16
 */
@Service
public class BackgroundSubtraction {

    private static final Logger LOG = LoggerFactory.getLogger(BackgroundSubtraction.class);

    private GrayF32 imageDifference;
    private GrayU8 imageBinary;

    private BufferedImage refImageDimension;

    /**
     * Calculate the background difference
     * @param bufferedImage the compare with background
     * @return the number of white pixels
     */
    public Long calculationBackSubtraction(BufferedImage bufferedImage) {

        refImageDimension = bufferedImage;

        // Load Background image
        BufferedImage imageBack = UtilImageIO.loadImage("/Users/ANDRE/Desktop/Background.jpg");

        imageBack = resize(imageBack);

        // convert into GrayScale
        GrayF32 inputBack = ConvertBufferedImage.convertFromSingle(imageBack, null, GrayF32.class);
        GrayF32 input = ConvertBufferedImage.convertFromSingle(bufferedImage, null, GrayF32.class);

        // Calculate difference B - A
        calculateDifference(inputBack, input);

        // Apply Threshold
        applyThreshold(imageDifference, 15);

        long whiteBytes = calculateDifference(imageBinary);

        // Display Result
//        displayImage(imageBinary);

        return Long.valueOf(whiteBytes);
    }

    /**
     * Calculate the difference between two image (GrayF32 image only)
     * imageB - imaegA
     *
     * @param imageA to compare
     * @param imageB to compare
     */
    void calculateDifference(GrayF32 imageA, GrayF32 imageB) {

        GrayF32 diff2 = new GrayF32(refImageDimension.getWidth(), refImageDimension.getHeight());
        GPixelMath.diffAbs(imageA, imageB, diff2);
        this.imageDifference = diff2;
    }

    /**
     * Apply a threshold on the image result. Every pixel under thresholdLevel is replace by
     * black, or white either
     *
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
     *
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
     * Display result into awt window
     *
     * @param imageBinary to display
     */
    void displayImage(GrayU8 imageBinary) {

        BufferedImage imageOut = new BufferedImage(imageBinary.width, imageBinary.height, BufferedImage.TYPE_BYTE_BINARY);
        VisualizeBinaryData.renderBinary(imageBinary, true, imageOut);
        // Display multiple images in the same window
        ListDisplayPanel gui = new ListDisplayPanel();
        gui.addImage(imageOut, "Image subtraction");

        ShowImages.showWindow(gui, "Background RESULT");
    }

    /**
     * Resize image to fit the input image size
     * @param bufferedImage to be resize
     * @return the resized image
     */
    BufferedImage resize(BufferedImage bufferedImage) {

        Thumbnails.Builder builder = Thumbnails.of(bufferedImage)
                .size(refImageDimension.getWidth(), refImageDimension.getHeight());

        bufferedImage = asBufferedImage(builder);

        return bufferedImage;
    }

    /**
     * Handle IOException
     * @param builder get the builder in order to create buffered Image
     * @return the buffered Image
     */
    BufferedImage asBufferedImage(Thumbnails.Builder builder) {

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = builder.asBufferedImage();
        } catch (IOException e) {
            LOG.error(e.toString());
        }
        return bufferedImage;
    }
}
