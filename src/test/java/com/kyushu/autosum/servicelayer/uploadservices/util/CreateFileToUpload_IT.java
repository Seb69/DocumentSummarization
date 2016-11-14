package com.kyushu.autosum.servicelayer.uploadservices.util;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 11/05/16
 */
public class CreateFileToUpload_IT extends AbstractIntegrationTest{

    private CreateFileToUpload createFileToUpload;

    @Autowired
    public void setCreateFileToUpload(CreateFileToUpload createFileToUpload) {
        this.createFileToUpload = createFileToUpload;
    }

    @Mock
    protected Appender mockAppender;

    @Captor
    protected ArgumentCaptor<LoggingEvent> captorLoggingEvent;

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
