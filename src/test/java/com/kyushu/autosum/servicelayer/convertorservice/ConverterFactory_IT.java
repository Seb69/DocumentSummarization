package com.kyushu.autosum.servicelayer.convertorservice;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Tester : ConverterFactory
 *
 * @author ANDRE
 * @since 28/05/16
 */
public class ConverterFactory_IT extends AbstractIntegrationTest {

    private ConverterFactory converterFactory;

    @Autowired
    public void setConverterFactory(ConverterFactory converterFactory) {
        this.converterFactory = converterFactory;
    }

    @Test
    public void getConverter_FilePDF__ConverterPDF() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF_Server();

        // OPERATE
        Converter converter = converterFactory.getConverter(file);

        // CHECK
        assertEquals(true, converter instanceof ConverterPDF);


    }

    @Test
    public void getConverter_FilePPT__ConverterPPT() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT_Server();

        // OPERATE
        Converter converter = converterFactory.getConverter(file);

        // CHECK
        assertEquals(true, converter instanceof ConverterPPT);


    }

    @Test
    public void getConverter_FilePPTX__ConverterPPTX() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX();

        // OPERATE
        Converter converter = converterFactory.getConverter(file);

        // CHECK
        assertEquals(true, converter instanceof ConverterPPT);

    }

}
