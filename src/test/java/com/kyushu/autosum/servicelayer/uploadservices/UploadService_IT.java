package com.kyushu.autosum.servicelayer.uploadservices;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ANDRE on 26/04/16.
 */
public class UploadService_IT  extends AbstractIntegrationTest{

    private UploadServiceImpl uploadService;


    @Autowired
    public void setUploadService(UploadServiceImpl uploadService) {
        this.uploadService = uploadService;
    }


    @Test
    public void testUploadFile_FilePPT__File() throws Exception {

        // BUILD
        File filePPT = GenerateFile.createPPT();
        FileInputStream fileInputStream = new FileInputStream(filePPT);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file-data", filePPT.getName(), "",fileInputStream);

        // OPERATE
        File fileUploaded = uploadService.uploadFile(mockMultipartFile);

        // CHECK
        assertEquals(true, fileUploaded.isFile());


    }
    @Test
    public void testUploadFile_FilePPTX__File() throws Exception {

        // BUILD
        File filePPTX = GenerateFile.createPPTX();
        FileInputStream fileInputStream = new FileInputStream(filePPTX);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file-data", filePPTX.getName(), "",fileInputStream);

        // OPERATE
        File fileUploaded = uploadService.uploadFile(mockMultipartFile);

        // CHECK
        assertEquals(true, fileUploaded.isFile());

    }

    @Test
    public void testUploadFile_FilePDF__File() throws Exception {

        // BUILD
        File filePDF = GenerateFile.createPDF();
        FileInputStream fileInputStream = new FileInputStream(filePDF);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file-data", filePDF.getName(), "",fileInputStream);

        // OPERATE
        File fileUploaded = uploadService.uploadFile(mockMultipartFile);

        // CHECK
        assertEquals(true, fileUploaded.isFile());

    }


}
