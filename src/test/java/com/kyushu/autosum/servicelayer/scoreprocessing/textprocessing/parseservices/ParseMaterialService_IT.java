package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

/**
 * Created by ANDRE on 29/04/16.
 */
public class ParseMaterialService_IT extends AbstractIntegrationTest {

    private ParseMaterialServiceImpl parseMaterialService;

    private ParserFactory parserFactory;

    @Autowired
    public void setParseMaterialService(ParseMaterialServiceImpl parseMaterialService) {
        this.parseMaterialService = parseMaterialService;
    }

    @Autowired
    public void setParserFactory(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }


    @Test
    public void parseMaterial_FilePDF__Material() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        Material material = parseMaterialService.parseMaterial(file);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getText().isEmpty());
        assertEquals(false, material.getSlideList().get(0).getText().isEmpty());

    }

    @Test
    public void parseMaterial_FilePPT__Material() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // OPERATE
        Material material = parseMaterialService.parseMaterial(file);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getText().isEmpty());
        assertEquals(false, material.getSlideList().get(0).getText().isEmpty());

    }

    @Test
    public void parseMaterial_FilePPTX__Material() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX();

        // OPERATE
        Material material = parseMaterialService.parseMaterial(file);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getText().isEmpty());
        assertEquals(false, material.getSlideList().get(0).getText().isEmpty());

    }

}
