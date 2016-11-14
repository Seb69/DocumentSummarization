package com.kyushu.autosum.servicelayer.uploadservices.util;

import com.kyushu.autosum.servicelayer.uploadservices.util.CreateFileToUpload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import static org.junit.Assert.assertEquals;
import static thredds.featurecollection.FeatureCollectionConfig.PartitionType.file;

/**
 * @author ANDRE
 * @since 11/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateFileToUpload_Test {

    @InjectMocks
    private CreateFileToUpload createFileToUpload = new CreateFileToUpload();

    @Test
    public void createFileToUpload() throws Exception {

        // OPERATE
        File file = createFileToUpload.createFileToUpload("originalName.pdf");

        // CHECK
        assertEquals(false, file.getPath().isEmpty());
    }

//    @Test
//    public void generateRandom4Characters() throws Exception {
//
//        // OPERATE
//        String string = createFileToUpload.generateRandom4Characters();
//
//        // CHECK
//        assertEquals(4, string.length());
//    }
}
