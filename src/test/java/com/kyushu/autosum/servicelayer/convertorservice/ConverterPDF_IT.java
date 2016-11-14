package com.kyushu.autosum.servicelayer.convertorservice;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

/**
 * Tester : ConverterPDF
 *
 * @author ANDRE
 * @since 28/05/16
 */
public class ConverterPDF_IT extends AbstractIntegrationTest {

    private ConverterPDF converterPDF;

    @Autowired
    public void setConverterPDF(ConverterPDF converterPDF) {
        this.converterPDF = converterPDF;
    }


    @Test
    public void converter_File1__BufferedImageList() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF_Single();

        // OPERATE
        List<BufferedImage> bufferedImageList = converterPDF.convert(file);

        // CHECK
        assertEquals(1,bufferedImageList.size());

    }

    @Test
    public void converter_File3__BufferedImageList() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF_Server();

        // OPERATE
        List<BufferedImage> bufferedImageList = converterPDF.convert(file);

        // CHECK
        assertEquals(3,bufferedImageList.size());

    }



}
