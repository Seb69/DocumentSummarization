package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 15/05/16
 */
public class ParseMaterialKeywordService_IT extends AbstractIntegrationTest {

    private ParseMaterialKeywordService parseMaterialKeywordService;

    @Autowired
    public void setParseMaterialKeywordService(ParseMaterialKeywordService parseMaterialKeywordService) {
        this.parseMaterialKeywordService = parseMaterialKeywordService;
    }

    @Test
    public void parseMaterialKeyword_MaterialEN__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglishEmptyKeywordList();

        // OPERATE
        material = parseMaterialKeywordService.parseMaterialKeyword(material);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getSlideList().isEmpty());
    }

    @Test
    public void parseMaterialKeyword_MaterialJP__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createJapanesesEmptyKeywordList();

        // OPERATE
        material = parseMaterialKeywordService.parseMaterialKeyword(material);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getSlideList().isEmpty());
    }
}
