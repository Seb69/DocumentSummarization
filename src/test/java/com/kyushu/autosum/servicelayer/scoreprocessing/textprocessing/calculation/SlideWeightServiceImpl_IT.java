package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Tester : SlideWeightServiceImpl
 *
 * @author ANDRE
 * @since 19/05/16
 */
public class SlideWeightServiceImpl_IT extends AbstractIntegrationTest {

    private SlideWeightServiceImpl slideWeightServiceImpl;

    @Autowired
    public void setSlideWeightServiceImpl(SlideWeightServiceImpl slideWeightServiceImpl) {
        this.slideWeightServiceImpl = slideWeightServiceImpl;
    }

    @Test
    public void calculateSlideWeight_Material__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish_Input();

        // OPERATE
        Material materialReturn = slideWeightServiceImpl.calculateSlideWeight(material);

        // CHECK
        assertNotEquals(null, materialReturn.getSlideList().get(0).getTextScore());
        DisplayData.output(materialReturn);
    }

}
    