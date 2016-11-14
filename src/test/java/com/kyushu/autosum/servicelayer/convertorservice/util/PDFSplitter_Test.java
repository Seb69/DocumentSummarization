package com.kyushu.autosum.servicelayer.convertorservice.util;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.kyushu.autosum.configurationlayer.LoggerTest;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.mockito.*;
import org.junit.*;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tester : PDFSplitter
 *
 * @author ANDRE
 * @since 25/05/16
 */
public class PDFSplitter_Test extends LoggerTest {

    @Test
    public void slitPDF_FilePDF__GoodNumberOfSlideCreated() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF_Server();

        // OPERATE
        pDFSplitter.splitPDF(file);

        // CHECK
        List<LoggingEvent> loggingEvents = getLoggingEvents();
        assertEquals("PDF slides created: 3", loggingEvents.get(1).getMessage());

    }

    @Test
    public void slitPDF_FilePDFOriginal__GoodNumberOfSlideCreated() throws Exception {

        // BUILD
        File file = new File("src/main/resources/upload-dir/4cmU/4cmU-Presentation1.pdf");

        // OPERATE
        pDFSplitter.splitPDF(file);

        // CHECK
        List<LoggingEvent> loggingEvents = getLoggingEvents();
        assertEquals("Replaced read xref table with the results of a brute force search", loggingEvents.get(1).getMessage().trim());

    }

    @InjectMocks
    PDFSplitter pDFSplitter = new PDFSplitter();
}
