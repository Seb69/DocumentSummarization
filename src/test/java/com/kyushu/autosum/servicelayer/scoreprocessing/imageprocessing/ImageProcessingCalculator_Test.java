package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import boofcv.io.image.UtilImageIO;
import com.google.api.client.util.ArrayMap;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction.BackgroundCalculationService;
import com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction.InterFrameCalculationService;
import com.kyushu.autosum.servicelayer.convertorservice.ConvertToImageService;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

/**
 * Tester : ImageProcessingCalculator
 *
 * @author ANDRE
 * @since 30/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageProcessingCalculator_Test {

    @Mock
    BackgroundCalculationService backgroundCalculationService;

    @Mock
    InterFrameCalculationService interFrameCalculationService;

    @Mock
    ConvertToImageService convertToImageService;

    @Test
    public void calculateImageProcessingFactors() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT_Server();

        // STUB
        when(backgroundCalculationService.calculateBackgroundScore(anyListOf(BufferedImage.class))).thenReturn(Arrays.asList(new Double(1), new Double(2), new Double(3), new Double(4), new Double(5)));
        Map<Integer, Double> integerDoubleMap = new ArrayMap<>();
        integerDoubleMap.put(1, Double.valueOf(11));
        integerDoubleMap.put(2, Double.valueOf(22));
        integerDoubleMap.put(3, Double.valueOf(33));
        when(interFrameCalculationService.calculateInterFrameScore(anyListOf(BufferedImage.class))).thenReturn(integerDoubleMap);
        BufferedImage image1 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");
        BufferedImage image2 = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        List<BufferedImage> bufferedImageList = Arrays.asList(image1, image2);
        when(convertToImageService.ConvertToImage(any(File.class))).thenReturn(bufferedImageList);

        // OPERATE
        Material material = imageProcessingCalculator.calculateImageProcessingFactors(file, GenerateMaterial.createEnglish());

        // CHECK
        DisplayData.output(material);

    }

    @Test
    public void setBackgroundScore() throws Exception {

        // BUILD
        List<Double> doubleList = Arrays.asList(new Double(1), new Double(2), new Double(3));

        // OPERATE
        imageProcessingCalculator.setMaterial(GenerateMaterial.createEnglish());
        imageProcessingCalculator.setBackgroundScore(doubleList);

        // CHECK
        DisplayData.output(imageProcessingCalculator.getMaterial().toString());


    }

    @Test
    public void setInterFrameScore() throws Exception {

        // BUILD
        Map<Integer, Double> map = new HashMap<>();
        map.put(1, Double.valueOf(421));
        map.put(2, Double.valueOf(422));
        map.put(3, Double.valueOf(423));

        // OPERATE
        imageProcessingCalculator.setMaterial(GenerateMaterial.createEnglish());
        imageProcessingCalculator.setInterFrameScore(map);

        // CHECK
        DisplayData.output(imageProcessingCalculator.getMaterial().toString());

    }

    @InjectMocks
    ImageProcessingCalculator imageProcessingCalculator = new ImageProcessingCalculator();


}
