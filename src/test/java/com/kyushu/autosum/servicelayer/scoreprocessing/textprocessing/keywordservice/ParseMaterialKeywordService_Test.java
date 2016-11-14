package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util.JapaneseWordSeparator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

/**
 * @author ANDRE
 * @since 15/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class ParseMaterialKeywordService_Test {

    @Mock
    ParserKeywordFactory parserKeywordFactory;

    @Mock
    JapaneseWordSeparator japaneseWordSeparator;

    @Spy
    ParseMaterialKeywordServiceImpl parseMaterialKeywordServiceImplSpy;

    @InjectMocks
    ParseMaterialKeywordServiceImpl parseMaterialKeywordServiceImpl = new ParseMaterialKeywordServiceImpl();

    @Test
    public void parseMaterialKeyword_MaterialEN__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglishEmptyKeywordList();

        // STUB
        Mockito.when(parserKeywordFactory.getKeywordParser(anyString())).thenReturn(new EnglishKeywordParser());

        // OPERATE
        material = parseMaterialKeywordServiceImpl.parseMaterialKeyword(material);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getSlideList().isEmpty());
    }
}
