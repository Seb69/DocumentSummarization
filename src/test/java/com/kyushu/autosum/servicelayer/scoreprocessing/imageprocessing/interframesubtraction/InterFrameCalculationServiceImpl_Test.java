package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction;

import boofcv.io.image.UtilImageIO;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Tester : InterFrameCalculationServiceImpl
 *
 * @author ANDRE
 * @since 30/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class InterFrameCalculationServiceImpl_Test {

    @Mock
    InterFrameSubtraction interFrameSubtraction;

    @Test
    public void calculateInterFrameScore() throws Exception {

        // BUILD
        BufferedImage image0 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image1 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        BufferedImage image2 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/Background.jpg");
        BufferedImage image3 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image4 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/Background.jpg");
        BufferedImage image5 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image6 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        List<BufferedImage> bufferedImageList = Arrays.asList(image0, image1, image2, image3, image4, image5, image6);

        // STUB
        when(interFrameSubtraction.calculationInterFrameSubtraction(any(BufferedImage.class), any(BufferedImage.class))).thenReturn(Long.valueOf(42));

        // OPERATE
        Map<Integer, Double> map = interFrameCalculationServiceImpl.calculateInterFrameScore(bufferedImageList);

        // CHECK
        DisplayData.output(map);

    }

    @InjectMocks
    InterFrameCalculationServiceImpl interFrameCalculationServiceImpl = new InterFrameCalculationServiceImpl();


}
