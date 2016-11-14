package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.generators.GenerateSlide;
import com.kyushu.autosum.repositorylayer.generators.GenerateString;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 14/05/16
 */
public class JapaneseKeywordParser_IT extends AbstractIntegrationTest {

    private JapaneseKeywordParser japaneseKeywordParser;

    @Autowired
    public void setJapaneseKeywordParser(JapaneseKeywordParser japaneseKeywordParser) {
        this.japaneseKeywordParser = japaneseKeywordParser;
    }

    @Test
    public void parseMaterial_Material__Matreial() throws Exception {

        // BUILD
        Material material= GenerateMaterial.createJapanesesEmptyKeywordList_Input();

        // OPERATE
        Material returnMaterial = japaneseKeywordParser.parseMaterial(material);

        // CHECK
        DisplayData.output(returnMaterial);
        assertEquals(false, returnMaterial.getText().isEmpty());
        assertEquals(false, returnMaterial.getSlideList().get(0).getText().isEmpty());

    }

    @Test
    public void concatSlideParsing_Material__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();
        material.setKeywordsList(Collections.EMPTY_LIST);
        DisplayData.input(material);

        // OPERATE
        Material returnMaterial = japaneseKeywordParser.concatSlideParsing(material);

        // CHECK
        DisplayData.output(returnMaterial);
        assertEquals(false,returnMaterial.getKeywordsList().isEmpty());

    }


    @Test
    public void parseSlides_SlideList__SlideList() throws Exception {

        // BUILD
        List<Slide> slideList = GenerateSlide.createJapaneseSlideListEmptyKeywordList_Input();

        // OPERATE
        slideList = japaneseKeywordParser.parseSlides(slideList);

        // CHECK
        assertEquals(false, slideList.isEmpty());
        DisplayData.output(slideList);

    }

    @Test
    public void separateJapaneseWord_StringJP__StringListJP_() throws Exception {

        // BUILD
        String string = GenerateString.createJapaneseString();

        // OPERATE
        List<String> slideListReturn = japaneseKeywordParser.separateJapaneseWord(string);

        // CHECK
        DisplayData.output(slideListReturn);
        assertEquals(false, slideListReturn.isEmpty());

    }
}
