package com.kyushu.autosum.weblayer.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kyushu.autosum.Application;
import com.kyushu.autosum.servicelayer.selection.DownloadSumFile;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;

/**
 * Tester : DownloadPDFController
 *
 * @author ANDRE
 * @since 18/06/16
 */
@RunWith(MockitoJUnitRunner.class)
public class DownloadPDFController_Test {

    @Mock
    DownloadSumFile downloadSumFile;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(downloadPDFController).build();
    }

    @Test
    public void downloadPDFFile() throws Exception {

        // BUILD
        File psfMerge = new File(Application.UploadDir.concat("/4cmU/MERGE_FILE.pdf"));

        // STUB
        Mockito.when(downloadSumFile.downloadFile(anyInt())).thenReturn(psfMerge);

        // OPERATE
//        mockMvc.perform(get("/materialPDF/1").param("time","120"))
        mockMvc.perform(get("/materialPDF/1"))
//                .andExpect(status().isOk())
                .andDo(print());

        // CHECK


    }

    @Test
    public void classPathTest() throws Exception {

        // BUILD


        // OPERATE


        // CHECK


    }

    @InjectMocks
    DownloadPDFController downloadPDFController;

}
