package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction;

import boofcv.io.image.UtilImageIO;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertNotEquals;

/**
 * Tester : BackgroundSubtraction
 *
 * @author ANDRE
 * @since 20/05/16
 */
public class BackgroundSubtraction_IT extends AbstractIntegrationTest {

    private BackgroundSubtraction backgroundSubtraction;

    @Autowired
    public void setBackgroundSubtraction(BackgroundSubtraction backgroundSubtraction) {
        this.backgroundSubtraction = backgroundSubtraction;
    }

    @Test
    public void backgroundSubtraction() throws Exception {

        // BUILD
        BufferedImage image = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");

        // OPERATE
        Long whitePixel = backgroundSubtraction.calculationBackSubtraction(image);

        // CHECK
        assertNotEquals(Long.valueOf(0), whitePixel);
    }


}
    