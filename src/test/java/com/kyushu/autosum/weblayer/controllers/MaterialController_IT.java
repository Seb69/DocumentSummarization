package com.kyushu.autosum.weblayer.controllers;

import com.kyushu.autosum.Application;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ANDRE on 19/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SuppressWarnings("Duplicates")
@WebAppConfiguration
public class MaterialController_IT {

    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetMaterials() throws Exception {

        mockMvc.perform(get("/materials"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andDo(print());
    }

    @Test
    public void testGetMaterial() throws Exception {

        mockMvc.perform(get("/material/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andDo(print());
    }

    @Test
    public void testPostMaterial_PDF() throws Exception {

        // PURE JSON REQUEST
//        final String TITLE ="TITLE 42";
//
//        Material material = new Material();
//        material.setTitle(TITLE);
//
//        Gson gson = new Gson();
//        String jsonObject = gson.toJson(material);
//
//
//        mockMvc.perform(post("/materials")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonObject))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.title", is(TITLE)))
//                .andDo(print());

        // BUILD
        File file = GenerateFile.createPPT();
        System.out.println(file.isFile()+"  "+file.getName()+file.exists());
        // Create multipart file
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", file.getName(), "multipart/form-data",fileInputStream);

        // OPERATE
        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/materials")
                .file(mockMultipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andDo(print());
        // OPERATE
        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/materials")
                .file(mockMultipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andDo(print());

    }
    @Test
    public void testPostMaterial_PPT__Material() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();
        // Create multipart file
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", file.getName(), "multipart/form-data", fileInputStream);

        // OPERATE
        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/materials")
                .file(mockMultipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andDo(print());

    }
}
