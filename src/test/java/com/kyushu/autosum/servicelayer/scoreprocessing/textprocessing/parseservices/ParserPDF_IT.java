package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 13/05/16
 */
public class ParserPDF_IT extends AbstractIntegrationTest {

    private ParserPDF parserPDF;

    @Autowired
    public void setParserPDF(ParserPDF parserPDF) {
        this.parserPDF = parserPDF;
    }

    @Test
    public void parseFile_FilePDF__StringText() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        String textParsed = parserPDF.parseFullText(file);

        // CHECK
        DisplayData.output(textParsed);
        assertEquals(false, textParsed.isEmpty());

    }

    @Test
    public void slideText_FilePDF__StringText() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        List<String> stringList = parserPDF.parseSlideText(file);

        // CHECK
        DisplayData.output(stringList);
        assertEquals(false, stringList.isEmpty());

    }


    @Test
    public void removeEscapeCharacter_String__String() throws Exception {

        // BUILD
        char escape = '\f';

        // OPERATE
        String string = parserPDF.removeEscapeCharacter(escape);

        // CHECK
        assertEquals(true,string.isEmpty());

    }
}
