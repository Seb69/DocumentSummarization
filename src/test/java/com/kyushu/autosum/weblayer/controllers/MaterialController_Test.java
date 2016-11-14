package com.kyushu.autosum.weblayer.controllers;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.ParseMaterialKeywordService;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices.ParseMaterialService;
import com.kyushu.autosum.servicelayer.uploadservices.UploadServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by ANDRE on 18/04/16.
 */
    @SuppressWarnings("Duplicates")
public class MaterialController_Test {

    @Mock //Mockito Mock object
    private MaterialService materialService;

    @Mock
    private UploadServiceImpl uploadService;

    @Mock
    private ParseMaterialService parseMaterialService;

    @Mock
    ParseMaterialKeywordService parseMaterialKeywordService;


    @InjectMocks //setups up controller, and injects mock objects into it.
    private MaterialController materialController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //initilizes controller and mocks
        mockMvc = MockMvcBuilders.standaloneSetup(materialController).build();
    }

    @Test
    public void testGetMaterials() throws Exception {

        final Integer ID = 1;

        List<Material> materialList = new ArrayList<Material>();
        Material material = new Material();
        material.setId(ID);
        materialList.add(material);

        Mockito.when(materialService.findAll()).thenReturn(materialList);

        mockMvc.perform(get("/materials"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(ID)))
                .andDo(print());
    }

    @Test
    public void testGetMaterial() throws Exception {

        final Integer ID = 1;

        Material material = new Material();
        material.setId(ID);
        Optional<Material> materialOptional = Optional.ofNullable(material);

        Mockito.when(materialService.findById(ID)).thenReturn(materialOptional);

        mockMvc.perform(get("/material/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(ID)))
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
//        Mockito.when(materialService.save(any(Material.class))).thenReturn(Optional.ofNullable(material));
//
//        mockMvc.perform(post("/materials")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonObject))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.title", is(TITLE)))
//                .andDo(print());


        File file = new File("/Users/ANDRE/Desktop/AutoSum/Backend-AutoSum/IMAGES/Presentation1.pdf");
        System.out.println(file.isFile() + "  " + file.getName() + file.exists());

        // Create multipart file
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file-data", file.getName(), "multipart/form-data", fileInputStream);

        Mockito.when(uploadService.uploadFile(any(MultipartFile.class))).thenReturn(file);

        // *************************
        Material material = new Material();
        material.setText("HELLO WORLD !");
        material.setMaterialFilePATH(file.getAbsolutePath());
        List<Slide> slideList = new ArrayList<>();

        Slide slide1 = new Slide();
        slide1.setText("Hello world ! First");
        slideList.add(slide1);

        Slide slide2 = new Slide();
        slide2.setText("Hello world ! Second");
        slideList.add(slide2);

        material.setSlideList(slideList);

        Mockito.when(parseMaterialService.parseMaterial(any(File.class))).thenReturn(material);
        Mockito.when(parseMaterialKeywordService.parseMaterialKeyword(any(Material.class))).thenReturn(material);

        // *****************************
        Material materialSaved = new Material();
        materialSaved.setText("HELLO");

        Optional<Material> materialOptional = Optional.of(materialSaved);

        Mockito.when(materialService.save(any(Material.class))).thenReturn(materialOptional);

        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/materials")
                .file(mockMultipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void testPostMaterial_PPT() throws Exception {

        // BUILD
        File file = new File("/Users/ANDRE/Desktop/AutoSum/Backend-AutoSum/IMAGES/Presentation1.ppt");
        // Create multipart file
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file-data", file.getName(), "multipart/form-data", fileInputStream);

        Mockito.when(uploadService.uploadFile(any(MultipartFile.class))).thenReturn(file);

        // *************************
        Material material = new Material();
        material.setText("HELLO WORLD !");
        material.setMaterialFilePATH(file.getAbsolutePath());
        List<Slide> slideList = new ArrayList<>();

        Slide slide1 = new Slide();
        slide1.setText("Hello world ! First");
        slideList.add(slide1);

        Slide slide2 = new Slide();
        slide2.setText("Hello world ! Second");
        slideList.add(slide2);

        material.setSlideList(slideList);

        Mockito.when(parseMaterialService.parseMaterial(any(File.class))).thenReturn(material);
        Mockito.when(parseMaterialKeywordService.parseMaterialKeyword(any(Material.class))).thenReturn(material);

        // *****************************
        Material materialSaved = new Material();
        materialSaved.setText("HELLO");

        Optional<Material> materialOptional = Optional.of(materialSaved);

        Mockito.when(materialService.save(any(Material.class))).thenReturn(materialOptional);

        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/materials")
                .file(mockMultipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void testPostMaterial_PPTX() throws Exception {


        File file = new File("/Users/ANDRE/Desktop/AutoSum/Backend-AutoSum/IMAGES/Presentation1.pptx");
        System.out.println(file.isFile()+"  "+file.getName()+file.exists());

        // Create multipart file
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file-data", file.getName(), "multipart/form-data", fileInputStream);

        Mockito.when(uploadService.uploadFile(any(MultipartFile.class))).thenReturn(file);

        // *************************
        Material material = new Material();
        material.setText("HELLO WORLD !");
        material.setMaterialFilePATH(file.getAbsolutePath());
        List<Slide> slideList = new ArrayList<>();

        Slide slide1 = new Slide();
        slide1.setText("Hello world ! First");
        slideList.add(slide1);

        Slide slide2 = new Slide();
        slide2.setText("Hello world ! Second");
        slideList.add(slide2);

        material.setSlideList(slideList);

        Mockito.when(parseMaterialService.parseMaterial(any(File.class))).thenReturn(material);
//        Mockito.when(parseMaterialKeywordService.parseMaterial(any(Material.class))).thenReturn(material);

        // *****************************
        Material materialSaved = new Material();
        materialSaved.setText("HELLO");

        Optional<Material> materialOptional = Optional.of(materialSaved);

        Mockito.when(materialService.save(any(Material.class))).thenReturn(materialOptional);

        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/materials")
                .file(mockMultipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andDo(print());
    }


    @Test
    public void getMaterialByUserId() throws Exception {

        // BUILD


        // MOCK
        Mockito.when(materialService.findByUserId(anyString())).thenReturn(Arrays.asList(new Material()));

        // OPERATE
        mockMvc.perform(get("/materials/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is(ID)))
                .andDo(print());

        // CHECK


    }
}
