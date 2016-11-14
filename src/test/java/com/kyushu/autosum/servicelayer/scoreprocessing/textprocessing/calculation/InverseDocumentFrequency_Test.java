package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.generators.GenerateSlide;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tester : InverseDocumentFrequency
 *
 * @author ANDRE
 * @since 18/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class InverseDocumentFrequency_Test {

    @Test
    public void calculationInverseTermFrequency() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish_Input();

        // OPERATE
        Map<String, Double> mapList = InverseDocumentFrequency.calculationInverseTermFrequency(material);

        // CHECK
        DisplayData.output(mapList);
        assertEquals(false, mapList.isEmpty());
        assertEquals(9, mapList.size());


    }

    @Test
    public void numberOfPages() throws Exception {

        // BUILD
        List<Slide> slideList = GenerateSlide.createEnglishSlideList_Input();

        // OPERATE
        long numberOfSlide = InverseDocumentFrequency.numberOfPages(slideList);

        // CHECK
        DisplayData.output(numberOfSlide);
        assertEquals(slideList.size(), numberOfSlide);

    }

    @Test
    public void isWordInsideSlide() throws Exception {

        // BUILD
        Slide slide = GenerateSlide.createEnglish_Input();

        // OPERATE
        boolean isWordInside = InverseDocumentFrequency.isWordInsideSlide(slide, "slide");

        // CHECK
        DisplayData.output(isWordInside);
        assertEquals(true,isWordInside);

    }

    @Test
    public void calculationOccurrenceWord() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish_Input();

        // OPERATE
        Map<String, Long> occurrence = InverseDocumentFrequency.calculationOccurrenceWord(material);

        // CHECK
        DisplayData.output(occurrence);
        assertEquals(false, occurrence.isEmpty());

    }
}
    