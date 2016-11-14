package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction;

import boofcv.io.image.UtilImageIO;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

/**
 * Tester : BackgroundCalculationServiceImpl
 *
 * @author ANDRE
 * @since 04/06/16
 */
public class BackgroundCalculationServiceImpl_IT extends AbstractIntegrationTest {

    private BackgroundCalculationServiceImpl backgroundCalculationServiceImpl;

    @Autowired
    public void setBackgroundCalculationServiceImpl(BackgroundCalculationServiceImpl backgroundCalculationServiceImpl) {
        this.backgroundCalculationServiceImpl = backgroundCalculationServiceImpl;
    }

    @Test
    public void calculateBackgroundScore() throws Exception {

        // BUILD
        BufferedImage image1 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image2 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        List<BufferedImage> bufferedImageList = Arrays.asList(image1, image2);

        // OPERATE
        List<Double> doubleList = backgroundCalculationServiceImpl.calculateBackgroundScore(bufferedImageList);

        // CHECK
        DisplayData.output(doubleList);
        assertNotEquals(0, doubleList.get(0));

    }

    @Test
    public void imageProcessingFactor() throws Exception {

        // BUILD
        List<Long> longs = Arrays.asList(Long.valueOf(42), Long.valueOf(42));

        // OPERATE
        List<Double> doubleList = backgroundCalculationServiceImpl.imageProcessingFactor(longs);

        // CHECK
        assertNotEquals(0, doubleList.get(0));

    }

}
    