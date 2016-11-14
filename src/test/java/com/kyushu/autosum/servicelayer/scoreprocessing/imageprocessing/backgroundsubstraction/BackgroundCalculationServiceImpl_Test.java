package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import boofcv.io.image.UtilImageIO;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * Tester : BackgroundCalculationServiceImpl
 *
 * @author ANDRE
 * @since 30/05/16
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(BackgroundSubtraction.class)
public class BackgroundCalculationServiceImpl_Test {

    @InjectMocks
    BackgroundCalculationServiceImpl backgroundCalculationServiveImpl = new BackgroundCalculationServiceImpl();

    @Mock
    BackgroundSubtraction backgroundSubtraction;

    @Test
    public void calculateBackgroundScore() throws Exception {

        // BUILD
        BufferedImage image1 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image2 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        List<BufferedImage> bufferedImageList = Arrays.asList(image1, image2);

        // MOCK
        Mockito.when(backgroundSubtraction.calculationBackSubtraction(any(BufferedImage.class))).thenReturn(Long.valueOf(42));

        // OPERATE
        List<Double> doubleList = backgroundCalculationServiveImpl.calculateBackgroundScore(bufferedImageList);

        // CHECK
        DisplayData.output(doubleList);
        assertNotEquals(0, doubleList.get(0));

    }

    @Test
    public void imageProcessingFactor() throws Exception {

        // BUILD
        List<Long> longs = Arrays.asList(Long.valueOf(42), Long.valueOf(42));

        // OPERATE
        List<Double> doubleList = backgroundCalculationServiveImpl.imageProcessingFactor(longs);

        // CHECK
        assertNotEquals(0, doubleList.get(0));

    }


}
    