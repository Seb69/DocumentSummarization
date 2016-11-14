package com.kyushu.autosum.servicelayer.convertorservice.util;

import static org.junit.Assert.*;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.runner.RunWith;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * Tester : ConverterToImage
 *
 * @author ANDRE
 * @since 25/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class ConverterToImage_Test {

    @Test
    public void convertToImage_File__BufferImage() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        List<BufferedImage> bufferedImageList = ConverterToImage.convertToImage(file);

        // CHECK
        assertEquals(3,bufferedImageList.size());

        // CREATE THE FILE FROM BUFFERED IMAGE TO CHECK THE RESULT
//        bufferedImageList.stream()
//                .forEach(bufferedImage -> {
//
//                    File file1;
//                    try {
//                        file1 = new File("src/main/resources/Hello"+ RandomStringUtils.random(4, true, true));
//                        file1.createNewFile();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    try {
//                        ImageIO.write(bufferedImage, "jpg", file1);
//                    } catch (IOException e) {
//                    }
//
//                });

    }
}
