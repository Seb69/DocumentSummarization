package com.kyushu.autosum.servicelayer.convertorservice.util;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tester : ConverterToImage
 *
 * @author ANDRE
 * @since 27/05/16
 */
public class ConverterToImage_IT extends AbstractIntegrationTest {


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
