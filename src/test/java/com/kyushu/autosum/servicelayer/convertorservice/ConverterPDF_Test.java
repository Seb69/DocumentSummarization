package com.kyushu.autosum.servicelayer.convertorservice;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.servicelayer.convertorservice.util.ConverterToImage;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Tester : ConverterPDF
 *
 * @author ANDRE
 * @since 28/05/16
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ConverterToImage.class)
public class ConverterPDF_Test {

    @Test
    public void converter_File1__BufferedImageList() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF_Single();

        // STUB
        PowerMockito.spy(ConverterToImage.class);
        PowerMockito.doReturn(Arrays.asList(new BufferedImage(1, 2, 3))).when(ConverterToImage.class, "convertToImage", any(File.class));

        // OPERATE
        List<BufferedImage> bufferedImageList = converterPDF.convert(file);

        // CHECK
        assertEquals(1,bufferedImageList.size());

    }


    @InjectMocks
    ConverterPDF converterPDF = new ConverterPDF();


}
