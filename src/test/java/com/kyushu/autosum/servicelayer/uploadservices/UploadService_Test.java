package com.kyushu.autosum.servicelayer.uploadservices;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

/**
 * Created by ANDRE on 26/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class UploadService_Test {

    @InjectMocks
    UploadServiceImpl uploadService = new UploadServiceImpl();

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
