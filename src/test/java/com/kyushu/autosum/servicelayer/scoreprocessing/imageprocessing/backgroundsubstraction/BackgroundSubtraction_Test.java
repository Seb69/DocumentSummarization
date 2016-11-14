package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction;

import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayF32;
import com.sun.javafx.iio.ImageStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Tester : BackgroundSubtraction
 *
 * @author ANDRE
 * @since 20/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class BackgroundSubtraction_Test {

    @InjectMocks
    BackgroundSubtraction backgroundSubtraction = new BackgroundSubtraction();

    @Test
    public void backgroundSubtraction() throws Exception {

        // BUILD
        BufferedImage image = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");

        // OPERATE
        backgroundSubtraction.calculationBackSubtraction(image);

        // CHECK

    }

    @Test
    public void calculateDifference() throws Exception {

        // BUILD
        BufferedImage imageA = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        BufferedImage imageB = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        GrayF32 inputA = ConvertBufferedImage.convertFromSingle(imageA, null, GrayF32.class);
        GrayF32 inputB = ConvertBufferedImage.convertFromSingle(imageB, null, GrayF32.class);

        // OPERATE
        backgroundSubtraction.calculateDifference(inputA,inputB);

        // CHECK

    }

}
    