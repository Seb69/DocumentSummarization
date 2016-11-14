package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

/**
 * Created by ANDRE on 29/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParseMaterialService_Test {

    @Mock
    ParserFactory parserFactory;

    @Spy
    ParseMaterialServiceImpl parseMaterialServiceSpy;

    @InjectMocks
    ParseMaterialService parseMaterialService = new ParseMaterialServiceImpl();

    @Test
    public void parseMaterial_FilePDF__Material() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // STUB
        doReturn(new ParserPDF()).when(parseMaterialServiceSpy).createParser(any(File.class));

        // OPERATE
        Material material = parseMaterialServiceSpy.parseMaterial(file);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getText().isEmpty());
        assertEquals(false, material.getSlideList().get(0).getText().isEmpty());

    }

    @Test
    public void parseMaterial_FilePPT__Material() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // STUB
        doReturn(new ParserPPT()).when(parseMaterialServiceSpy).createParser(any(File.class));

        // OPERATE
        Material material = parseMaterialServiceSpy.parseMaterial(file);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getText().isEmpty());
        assertEquals(false, material.getSlideList().get(0).getText().isEmpty());

    }

    @Test
    public void parseMaterial_FilePPTX__Material() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX();

        // STUB
        doReturn(new ParserPPTX()).when(parseMaterialServiceSpy).createParser(any(File.class));

        // OPERATE
        Material material = parseMaterialServiceSpy.parseMaterial(file);

        // CHECK
        DisplayData.output(material);
        assertEquals(false, material.getText().isEmpty());
        assertEquals(false, material.getSlideList().get(0).getText().isEmpty());

    }

}