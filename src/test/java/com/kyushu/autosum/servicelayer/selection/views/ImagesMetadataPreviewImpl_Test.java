package com.kyushu.autosum.servicelayer.selection.views;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import com.kyushu.autosum.servicelayer.convertorservice.ConvertToImageService;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tester : ImagesMetadataPreviewImpl
 *
 * @author ANDRE
 * @since 17/08/16
 */
@RunWith(MockitoJUnitRunner.class)
public class ImagesMetadataPreviewImpl_Test {
    @Test
    public void originalImage() throws Exception {

        // BUILD
        Integer ID = 1;
        Material material = GenerateMaterial.createEnglish();
        BufferedImage bufferedImage = new BufferedImage(100, 200, BufferedImage.TYPE_3BYTE_BGR);

        // MOCK
        when(materialService.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(material));
        when(convertToImageService.ConvertToImage(any(File.class))).thenReturn(Arrays.asList(bufferedImage));

        // OPERATE
        List<File> fileList = downloadOriginalImagesImpl.originalImage(ID);

        // CHECK
        DisplayData.output(fileList);
//        assertEquals(fil);

    }

    @Test
    public void getMaterialLinks() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();

        // MOCK
        when(materialService.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(material));

        // OPERATE
        List<String> links = downloadOriginalImagesImpl.getMaterialPreviewLink(1);

        // CHECK
        DisplayData.output(links);


    }


    @Test
    public void testOrderList() throws Exception {

        // BUILD
        List<Integer> links = Arrays.asList(13,62,8,1,51);
        // Reorder links in ascanding way
        Comparator<Integer> bySlideId = (s1, s2) -> Integer.compare(s1, s2);

        List<Integer> linksOrdered = links.stream()
                .sorted(bySlideId)
                .collect(Collectors.toList());

        // MOCK


        // OPERATE


        // CHECK
        DisplayData.output(linksOrdered);

    }

    @InjectMocks
    ImagesMetadataPreviewImpl downloadOriginalImagesImpl = new ImagesMetadataPreviewImpl();

    @Mock
    MaterialService materialService;

    @Mock
    ConvertToImageService convertToImageService;


}
