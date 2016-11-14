package com.kyushu.autosum.servicelayer.selection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

/**
 * Tester : DownloadSumFileImpl
 *
 * @author ANDRE
 * @since 17/06/16
 */
@RunWith(MockitoJUnitRunner.class)
public class DownloadSumFileImpl_Test {

    @Mock
    SelectionSlides selectionSlides;

    @Mock
    CreateDownloadFile createDownloadFile;

    @Mock
    MaterialService materialService;

    @Spy
    DownloadSumFileImpl downloadSumFileSpy;

    @Test
    public void downloadFile() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();
        material.getSlideList().stream()
                .forEach(slide -> slide.setSelected(true));

        // STUB
        when(selectionSlides.selectionSlides(any(Material.class), anyInt())).thenReturn(material);
        when(createDownloadFile.createDownloadFile(any(Material.class))).thenReturn(GenerateFile.createPDF());
        when(materialService.findById(anyInt())).thenReturn(Optional.ofNullable(GenerateMaterial.createEnglish()));

        // OPERATE
        File file  = downloadSumFileImpl.downloadFile(1);

        // CHECK
        DisplayData.output(file);
        assertEquals(true, file.exists());

    }


    @InjectMocks
    DownloadSumFileImpl downloadSumFileImpl = new DownloadSumFileImpl();


}
