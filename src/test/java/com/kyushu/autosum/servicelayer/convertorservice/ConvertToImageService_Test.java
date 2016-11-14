package com.kyushu.autosum.servicelayer.convertorservice;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * Tester : ConvertToImageServiceImpl
 *
 * @author ANDRE
 * @since 29/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class ConvertToImageService_Test {

    @Mock
    ConverterFactory converterFactory;

    @Mock
    Converter converter;

    @Test
    public void convertToImage_FilePDF__BufferedImageList() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF_Server();

        // STUB
        when(converterFactory.getConverter(any(File.class))).thenReturn(new ConverterPDF());

        // OPERATE
        List<BufferedImage> bufferedImageList = convertToImageServiceImpl.ConvertToImage(file);

        // CHECK
        System.out.println(" Image dimension: " + bufferedImageList.get(0).getWidth() + " " + bufferedImageList.get(0).getHeight());
        assertEquals(3,bufferedImageList.size());
    }


//    @Test
//    public void convertToImage_FilePPT__BufferedImageList() throws Exception {
//
//        // BUILD
//        File file = GenerateFile.createPPT_Server();
//
//        // STUB
//        when(converterFactory.getConverter(any(File.class))).thenReturn(new ConverterPPT());
//        when(converter.convert(any(File.class))).thenReturn(Arrays.asList(new BufferedImage(1, 2, 3)));
//
//        // OPERATE
//        List<BufferedImage> bufferedImageList = convertToImageServiceImpl.ConvertToImage(file);
//
//        // CHECK
//        assertEquals(3,bufferedImageList.size());
//    }



    @InjectMocks
    ConvertToImageServiceImpl convertToImageServiceImpl = new ConvertToImageServiceImpl();

}
