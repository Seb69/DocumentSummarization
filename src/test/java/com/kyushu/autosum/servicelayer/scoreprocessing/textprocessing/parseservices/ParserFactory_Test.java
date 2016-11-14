package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

/**
 * Tester : ParserFactory
 *
 * @author ANDRE
 * @since 23/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class ParserFactory_Test {

    @InjectMocks
    ParserFactory parserFactory = new ParserFactory();

    @Spy
    ParserFactory parserFactorySpy;

    @Test
    public void getParser_FilePDF__ParserPDF() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // STUB
        doReturn("application/pdf").when(parserFactorySpy).getMimeType(any(File.class));

        // OPERATE
        Parser parser = parserFactorySpy.createParser(file);

        // CHECK
        assertEquals(true , parser instanceof ParserPDF);

    }
    @Test
    public void getParser_FilePPT__ParserPDF() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // STUB
        doReturn("application/vnd.ms-powerpoint").when(parserFactorySpy).getMimeType(any(File.class));

        // OPERATE
        Parser parser = parserFactorySpy.createParser(file);

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
    