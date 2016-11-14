package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction;

import boofcv.io.image.UtilImageIO;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Tester : InterFrameCalculationServiceImpl
 *
 * @author ANDRE
 * @since 04/06/16
 */
public class InterFrameCalculationServiceImpl_IT extends AbstractIntegrationTest {

    private InterFrameCalculationServiceImpl interFrameCalculationServiceImpl;

    @Autowired
    public void setInterFrameCalculationServiceImpl(InterFrameCalculationServiceImpl interFrameCalculationServiceImpl) {
        this.interFrameCalculationServiceImpl = interFrameCalculationServiceImpl;
    }

    @Test
    public void calculateInterFrameScore() throws Exception {

        // BUILD
        BufferedImage image0 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image1 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        BufferedImage image2 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/Background.jpg");
        BufferedImage image3 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image4 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/Background.jpg");
        BufferedImage image5 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        BufferedImage image6 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        List<BufferedImage> bufferedImageList = Arrays.asList(image0, image1, image2, image3, image4, image5, image6);

        // OPERATE
        Map<Integer, Double> map = interFrameCalculationServiceImpl.calculateInterFrameScore(bufferedImageList);

        // CHECK
        DisplayData.output(map);
        assertNotEquals(0, map.get(0));
        assertNotEquals(0, map.get(bufferedImageList.size()-1));

    }

}
    