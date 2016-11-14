package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import static org.junit.Assert.*;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;

/**
 * Tester : SlideWeightServiceImpl
 *
 * @author ANDRE
 * @since 19/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class SlideWeightServiceImpl_Test {

    @InjectMocks
    SlideWeightServiceImpl slideWeightServiceImpl = new SlideWeightServiceImpl();

    @Test
    public void calculateSlideWeight_MaterialEnglish__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish_Input();

        // OPERATE
        Material materialReturn = slideWeightServiceImpl.calculateSlideWeight(material);

        // CHECK
        assertNotEquals(null, materialReturn.getSlideList().get(0).getTextScore());
        DisplayData.output(materialReturn);
    }


    @Test
    public void calculateSlideWeight_MaterialJapanese__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createJapaneses_Input();

        // OPERATE
        Material materialReturn = slideWeightServiceImpl.calculateSlideWeight(material);

        // CHECK
        assertNotEquals(null, materialReturn.getSlideList().get(0).getTextScore());
        DisplayData.output(materialReturn);
    }


}
    