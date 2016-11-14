package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

/**
 * Tester : ParserFactory
 *
 * @author ANDRE
 * @since 23/05/16
 */
public class ParserFactory_IT extends AbstractIntegrationTest {

    private ParserFactory parserFactory;

    @Autowired
    public void setParserFactory(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Test
    public void getParser_FilePDF__ParserPDF() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        Parser parser = parserFactory.createParser(file);

        // CHECK
        assertEquals(true , parser instanceof ParserPDF);

    }
    @Test
    public void getParser_FilePPT__ParserPDF() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // OPERATE
        Parser parser = parserFactory.createParser(file);

        // CHECK
        assertEquals(true , parser instanceof ParserPPT);

    }

    @Test
    public void getMimeType_FilePDF__StringMime() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        String mimeType = parserFactory.getMimeType(file);

        // CHECK
        assertEquals("application/pdf", mimeType);

    }

}
    