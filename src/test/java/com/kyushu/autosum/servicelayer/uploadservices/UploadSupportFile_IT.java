package com.kyushu.autosum.servicelayer.uploadservices;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import uk.org.lidalia.slf4jtest.TestLogger;
//import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;


/**
 * @author ANDRE
 * @since 11/05/16
 */
public class UploadSupportFile_IT extends AbstractIntegrationTest{

    private UploadSupportFileImpl uploadSupportFile;

    @Autowired
    public void setUploadSupportFile(UploadSupportFileImpl uploadSupportFile) {
        this.uploadSupportFile = uploadSupportFile;
    }

    @Test
    public void testIsSupport_FilePDF__Boolean() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // OPERATE
        boolean support = uploadSupportFile.isSupport(file);

        // CHECK
        assertEquals(true, support);

    }

    @Test
    public void isSupport_FilePPT__Boolean() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // OPERATE
        boolean support = uploadSupportFile.isSupport(file);

        // CHECK
        assertEquals(true, support);
    }

    @Test
    public void isSupport_FilePPTX__Boolean() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX();

        // OPERATE
        boolean support = uploadSupportFile.isSupport(file);

        // CHECK
        assertEquals(true, support);
    }



    @Test
    public void isSupport_FileJPG__Boolean() throws Exception {

        // BUILD
        File file = GenerateFile.createJPG();

        // OPERATE
        boolean support = uploadSupportFile.isSupport(file);

        // CHECK
        assertEquals(false, support);
    }

    @Test
    public void getMimeType_FilePDF__String() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        String mimeType = uploadSupportFile.getMimeType(file);

        // CHECK
        assertEquals("application/pdf",mimeType);

    }

    @Test
    public void getMimeType_FilePPT__String() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // OPERATE
        String mimeType = uploadSupportFile.getMimeType(file);

        // CHECK
        assertEquals("application/vnd.ms-powerpoint",mimeType);

    }

    @Test
    public void getMimeType_FilePPTX__String() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX();

        // OPERATE
        String mimeType = uploadSupportFile.getMimeType(file);

        // CHECK
        assertEquals("application/vnd.openxmlformats-officedocument.presentationml.presentation",mimeType);

    }



}
