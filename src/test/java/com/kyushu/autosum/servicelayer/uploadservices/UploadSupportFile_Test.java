package com.kyushu.autosum.servicelayer.uploadservices;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.kyushu.autosum.configurationlayer.LoggerTest;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.servicelayer.uploadservices.exceptions.FileTypeNotDetect;
import com.kyushu.autosum.servicelayer.util.DetectMimeType;
import org.junit.Test;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * @author ANDRE
 * @since 11/05/16
 */
@PrepareForTest(DetectMimeType.class)
public class UploadSupportFile_Test extends LoggerTest {

    @Mock
    DetectMimeType detectMimeType;

    @Mock
    FileTypeNotDetect fileTypeNotDetect;

    UploadSupportFileImpl uploadSupportFileSpy = spy(new UploadSupportFileImpl());

    @InjectMocks
    UploadSupportFileImpl uploadSupportFile = new UploadSupportFileImpl();

    @Test
    public void testIsSupport_FilePDF__Boolean() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // STUB
        doReturn("application/pdf").when(uploadSupportFileSpy).getMimeType(any(File.class));

        // OPERATE
        boolean support = uploadSupportFileSpy.isSupport(file);

        // CHECK
        assertEquals(true, support);

    }

    @Test
    public void isSupport_FilePPT__Boolean() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // STUB
        doReturn("application/vnd.ms-powerpoint").when(uploadSupportFileSpy).getMimeType(any(File.class));

        // OPERATE
        boolean support = uploadSupportFileSpy.isSupport(file);

        // CHECK
        assertEquals(true, support);
    }

    @Test
    public void isSupport_FilePPTX__Boolean() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX();

        // STUB
        doReturn("application/vnd.openxmlformats-officedocument.presentationml.presentation").when(uploadSupportFileSpy).getMimeType(any(File.class));

        // OPERATE
        boolean support = uploadSupportFileSpy.isSupport(file);

        // CHECK
        assertEquals(true, support);
    }



    @Test
    public void isSupport_FileJPG__Boolean() throws Exception {

        // BUILD
        File file = GenerateFile.createJPG();

        // STUB
        doReturn("application/jpeg").when(uploadSupportFileSpy).getMimeType(any(File.class));

        // OPERATE
        boolean support = uploadSupportFileSpy.isSupport(file);

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


    @Test
    public void getMimeType_FileError__E_FileTypeNotDetect() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // STUB
        PowerMockito.mockStatic(DetectMimeType.class);
        PowerMockito.when(DetectMimeType.getMimeType(any(File.class))).thenThrow(new FileTypeNotDetect("hello"));

        // OPERATE
        uploadSupportFile.getMimeType(file);

        // CHECK
        LoggingEvent loggingEvent = getLoggingEvent();
        assertEquals("hello", loggingEvent.getMessage());

    }


}
