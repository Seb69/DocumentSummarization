package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction;

import static org.junit.Assert.*;

import boofcv.io.image.UtilImageIO;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

/**
 * Tester : InterFrameSubtraction
 *
 * @author ANDRE
 * @since 30/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class InterFrameSubtraction_Test {
    @Test
    public void calculationBackSubtraction() throws Exception {

        // BUILD
        BufferedImage image1 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image2 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");

        // OPERATE
        Long whitePixel = interFrameSubtraction.calculationInterFrameSubtraction(image1, image2);

        // CHECK
        assertNotEquals(0,String.valueOf(whitePixel));

    }

    @InjectMocks
    InterFrameSubtraction interFrameSubtraction = new InterFrameSubtraction();

}
