package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.generators.GenerateSlide;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tester : TermFrequency_InverseDocumentFrequency
 *
 * @author ANDRE
 * @since 19/05/16
 */
public class TermFrequency_InverseDocumentFrequency_IT extends AbstractIntegrationTest {

    @Test
    public void calculation_TF_IDF() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish_Input();

        // OPERATE
        List<Map<String, Double>> calculateReturn = TermFrequency_InverseDocumentFrequency.calculation_TF_IDF(material);

        // CHECK
        DisplayData.output(calculateReturn);
        assertEquals(false, calculateReturn.isEmpty());
    }

    @Test
    public void getTermFrequencyCalculationList() throws Exception {

        // BUILD
        List<Slide> slideList = GenerateSlide.createEnglishSlideList();


        // OPERATE
        List<Map<String, Long>> mapList =TermFrequency_InverseDocumentFrequency.getTermFrequencyCalculationList(slideList);

        // CHECK
        assertEquals(false, mapList.isEmpty());


    }
}
    