package com.kyushu.autosum.servicelayer.selection;

import com.kyushu.autosum.Application;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

/**
 * Tester : DownloadSumFile
 *
 * @author ANDRE
 * @since 17/06/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SuppressWarnings("Duplicates")
@WebAppConfiguration
public class DownloadSumFile_IT  {

    @Autowired
    private DownloadSumFile downloadSumFile;

    public void setDownloadSumFile(DownloadSumFileImpl downloadSumFile) {
        this.downloadSumFile = downloadSumFile;
    }

    @Test
    @Transactional
    public void downloadFile_ID__File() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();


        // OPERATE
        File file  = downloadSumFile.downloadFile(1);

        // CHECK
        DisplayData.output(file);
        assertEquals(true, file.exists());

    }


    @Test
    @Transactional
    public void findMaterial() throws Exception {

        // BUILD

        // OPERATE
        Material material = downloadSumFile.findMaterial(1);

        // CHECK
        DisplayData.output(material);
        assertNotEquals(true, material.equals(null));

    }
}
