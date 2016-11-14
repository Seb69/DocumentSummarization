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
 * Tester : ConvertToImageService
 *
 * @author ANDRE
 * @since 29/05/16
 */
public class ConvertToImageService_IT extends AbstractIntegrationTest {

    private ConvertToImageService convertToImageService;

    @Autowired
    public void setConvertToImageService(ConvertToImageService convertToImageService) {
        this.convertToImageService = convertToImageService;
    }


    @Test
    public void convertToImage_FilePDF__BufferedImageList() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF_Server();

        // OPERATE
        List<BufferedImage> bufferedImageList = convertToImageService.ConvertToImage(file);

        // CHECK
        assertEquals(3,bufferedImageList.size());
    }


    @Test
    public void convertToImage_FilePPT__BufferedImageList() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT_Server();

        // OPERATE
        List<BufferedImage> bufferedImageList = convertToImageService.ConvertToImage(file);

        // CHECK
        assertEquals(3,bufferedImageList.size());
    }



}
