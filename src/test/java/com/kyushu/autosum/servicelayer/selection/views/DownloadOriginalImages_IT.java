package com.kyushu.autosum.servicelayer.selection.views;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;

/**
 * Tester : ImagesMetadataPreview
 *
 * @author ANDRE
 * @since 17/08/16
 */
public class DownloadOriginalImages_IT extends AbstractIntegrationTest {

    private ImagesMetadataPreview imagesMetadataPreview;

    @Autowired
    public void setImagesMetadataPreview(ImagesMetadataPreview imagesMetadataPreview) {
        this.imagesMetadataPreview = imagesMetadataPreview;
    }

    @Test
    @Transactional
    public void originalImage() throws Exception {

        // BUILD
        Integer ID = 1;


        // OPERATE
        List<File> fileList = imagesMetadataPreview.originalImage(ID);

        // CHECK
        DisplayData.output(fileList);
//        assertEquals(fil);

    }
}
