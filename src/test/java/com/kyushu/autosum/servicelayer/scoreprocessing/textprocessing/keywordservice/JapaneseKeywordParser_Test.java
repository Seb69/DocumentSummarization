package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.generators.GenerateSlide;
import com.kyushu.autosum.repositorylayer.generators.GenerateString;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util.JapaneseWordSeparator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

/**
 * @author ANDRE
 * @since 14/05/16
 */
@RunWith(PowerMockRunner.class)
public class JapaneseKeywordParser_Test {

    @Mock
    JapaneseWordSeparator japaneseWordSeparator;

    @InjectMocks
    JapaneseKeywordParser japaneseKeywordParser = new JapaneseKeywordParser();

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

        // STUB
        Mockito.when(japaneseWordSeparator.parseJapaneseString(anyString())).thenReturn(Arrays.asList("デジタル", "画像", "を"));

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

        // STUB
        Mockito.when(japaneseWordSeparator.parseJapaneseString(anyString())).thenReturn(Arrays.asList("デジタル", "画像", "を"));

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

        // STUB
        Mockito.when(japaneseWordSeparator.parseJapaneseString(anyString())).thenReturn(Arrays.asList("デジタル", "画像", "を"));

        // OPERATE
        List<String> slideListreturn = japaneseKeywordParser.separateJapaneseWord(string);

        // CHECK
        DisplayData.output(slideListreturn);
        assertEquals(false, slideListreturn.isEmpty());

    }
}

