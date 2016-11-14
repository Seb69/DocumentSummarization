package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.generators.GenerateSlide;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 14/05/16
 */
public class EnglishKeywordParser_IT extends AbstractIntegrationTest {

    private EnglishKeywordParser englishKeywordParser;

    @Autowired
    public void setEnglishKeywordParser(EnglishKeywordParser englishKeywordParser) {
        this.englishKeywordParser = englishKeywordParser;
    }

    @Test
    public void parseMaterial() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglishEmptyKeywordList_Input();

        // OPERATE
        Material returnMaterial = englishKeywordParser.parseMaterial(material);

        // CHECK
        DisplayData.output(returnMaterial);
        assertEquals(false, returnMaterial.getText().isEmpty());
        assertEquals(false, returnMaterial.getSlideList().get(0).getText().isEmpty());


    }

    @Test
    public void concatSlideParsing() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish_Input();

        // OPERATE
        Material returnMaterial = englishKeywordParser.concatSlideParsing(material);

        // CHECK
        DisplayData.output(returnMaterial);
        assertEquals(false, returnMaterial.getKeywordsList().isEmpty());

    }

    @Test
    public void parseSlides() throws Exception {

        // BUILD
        List<Slide> slideList = GenerateSlide.createEnglishSlideListEmptyKeywordList_Input();

        // OPERATE
        slideList = englishKeywordParser.parseSlides(slideList);

        // CHECK
        DisplayData.output(slideList);
        assertEquals(false, slideList.isEmpty());

    }

}
