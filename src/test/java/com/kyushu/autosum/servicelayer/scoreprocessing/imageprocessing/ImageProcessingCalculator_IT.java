package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.*;

import static org.mockito.Matchers.any;

/**
 * Tester : ImageProcessingCalculator
 *
 * @author ANDRE
 * @since 30/05/16
 */
public class ImageProcessingCalculator_IT extends AbstractIntegrationTest {

    private ImageProcessingCalculator imageProcessingCalculator;

    @Autowired
    public void setImageProcessingCalculator(ImageProcessingCalculator imageProcessingCalculator) {
        this.imageProcessingCalculator = imageProcessingCalculator;
    }


    @Test
    public void calculateImageProcessingFactors() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT_Server();
        Material material = GenerateMaterial.createEnglish();

        // OPERATE
        material = imageProcessingCalculator.calculateImageProcessingFactors(file, material);

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
        map.put(0, Double.valueOf(1.1));
        map.put(1, Double.valueOf(2.2));
        map.put(2, Double.valueOf(3.3));

        // OPERATE
        imageProcessingCalculator.setMaterial(GenerateMaterial.createEnglish());
        imageProcessingCalculator.setInterFrameScore(map);

        // CHECK
        DisplayData.output(imageProcessingCalculator.getMaterial().getSlideList().toString());
//        assertEquals(imageProcessingCalculator.getMaterial().getSlideList());
    }
}
    