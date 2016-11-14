package com.kyushu.autosum.servicelayer.selection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.util.List;

/**
 * Tester : CreateDownloadFile
 *
 * @author ANDRE
 * @since 17/06/16
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateDownloadFile_Test {
    @Test
    public void createDownloadFile() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();
        List<Slide> slideList = material.getSlideList();
        slideList.get(0).setSelected(true);
        slideList.get(1).setSelected(true);
        slideList.get(2).setSelected(false);

        // OPERATE
        File file = createDownloadFile.createDownloadFile(material);

        // CHECK
        DisplayData.output(file);
        assertEquals(true,file.exists());


    }

    @InjectMocks
    CreateDownloadFile createDownloadFile = new CreateDownloadFile();


}
