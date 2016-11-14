package com.kyushu.autosum.servicelayer.uploadservices.util;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.servicelayer.util.DetectMimeType;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import uk.org.lidalia.slf4jtest.TestLogger;
//import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

/**
 * Tester : DetectMimeType
 *
 * @author ANDRE
 * @since 23/05/16
 */
public class DetectMimeType_IT extends AbstractIntegrationTest {

    private DetectMimeType detectMimeType;

    @Autowired
    public void setDetectMimeType(DetectMimeType detectMimeType) {
        this.detectMimeType = detectMimeType;
    }

    private static Logger logger = LoggerFactory.getLogger(DetectMimeType_IT.class);

    @Test
    public void detect_File__String() {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        String mime = DetectMimeType.detect(file);

        // CHECK
        assertEquals("application/pdf",mime);
    }

}
    