package com.kyushu.autosum.servicelayer.selection;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tester : CreateDownloadFile
 *
 * @author ANDRE
 * @since 18/06/16
 */
public class CreateDownloadFile_IT extends AbstractIntegrationTest {

    private CreateDownloadFile createDownloadFile;

    @Autowired
    public void setCreateDownloadFile(CreateDownloadFile createDownloadFile) {
        this.createDownloadFile = createDownloadFile;
    }

    @Test
    public void createDownloadFile() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();
        List<Slide> slideList = material.getSlideList();
        slideList.get(0).setSelected(true);
        slideList.get(1).setSelected(false);
        slideList.get(2).setSelected(true);

        // OPERATE
        File file = createDownloadFile.createDownloadFile(material);

        // CHECK
        DisplayData.output(file);
        assertEquals(true,file.exists());

    }
}
