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
 * Tester : ConverterPPT
 *
 * @author ANDRE
 * @since 28/05/16
 */
public class ConverterPPT_IT extends AbstractIntegrationTest {

    private ConverterPPT converterPPT;

    @Autowired
    public void setConverterPPT(ConverterPPT converterPPT) {
        this.converterPPT = converterPPT;
    }


    @Test
    public void converter() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT_Server();

        // OPERATE
        List<BufferedImage> bufferedImageList = converterPPT.convert(file);

        // CHECK
        assertEquals(3, bufferedImageList.size());

    }

}
