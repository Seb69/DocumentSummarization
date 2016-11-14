package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.generators.GenerateSlide;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 14/05/16
 */
@RunWith(PowerMockRunner.class)
public class EnglishKeywordParser_Test {

    @InjectMocks
    EnglishKeywordParser englishParserKeyword = new EnglishKeywordParser();

    @Test
    public void parseMaterial() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglishEmptyKeywordList_Input();

        // OPERATE
        Material returnMaterial = englishParserKeyword.parseMaterial(material);

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
        Material returnMaterial = englishParserKeyword.concatSlideParsing(material);

        // CHECK
        DisplayData.output(returnMaterial);
        assertEquals(false, returnMaterial.getKeywordsList().isEmpty());

    }

    @Test
    public void parseSlides() throws Exception {

        // BUILD
        List<Slide> slideList = GenerateSlide.createEnglishSlideListEmptyKeywordList_Input();

        // OPERATE
        slideList = englishParserKeyword.parseSlides(slideList);

        // CHECK
        DisplayData.output(slideList);
        assertEquals(false, slideList.isEmpty());

    }
}
