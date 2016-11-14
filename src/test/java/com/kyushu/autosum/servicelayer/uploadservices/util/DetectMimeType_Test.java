package com.kyushu.autosum.servicelayer.uploadservices.util;

import static com.kyushu.autosum.servicelayer.util.DetectMimeType.detect;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import ch.qos.logback.classic.spi.LoggingEvent;
import com.kyushu.autosum.configurationlayer.LoggerTest;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.servicelayer.uploadservices.exceptions.FileTypeNotDetect;
import com.kyushu.autosum.servicelayer.util.DetectMimeType;
import org.mockito.*;
import org.junit.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;


import java.io.File;

/**
 * Tester : DetectMimeType
 *
 * @author ANDRE
 * @since 22/05/16
 */
@PrepareForTest(DetectMimeType.class)
public class DetectMimeType_Test extends LoggerTest {

    @InjectMocks
    DetectMimeType detectMimeType = new DetectMimeType();

    @Test(expected = FileTypeNotDetect.class)
    public void getMimeType__E_FileTypeNotDetect() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // STUB
        PowerMockito.spy(DetectMimeType.class);
        PowerMockito.doReturn("octet-stream").when(DetectMimeType.class, "detect", any(File.class));

        // OPERATE
        DetectMimeType.getMimeType(file);

    }

    @Test
    public void detect_FileError__E_FileNotFound() {

        // BUILD
        File file = GenerateFile.createError();

        // OPERATE
        detect(file);

        // CHECK
        LoggingEvent loggingEvent = getLoggingEvent();
        assertEquals("NoFile (No such file or directory)", loggingEvent.getMessage());

    }
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