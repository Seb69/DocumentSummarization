package com.kyushu.autosum.repositorylayer.repositoryservice;

import com.kyushu.autosum.Application;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.junit.Assert.assertEquals;

/**
 * Created by ANDRE on 24/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SuppressWarnings("Duplicates")
@WebAppConfiguration
public class MaterialServiceIT {

    private MaterialService materialService;

    @Autowired
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Test
    public void testFindAll() throws Exception{

        List<Material> materialList = materialService.findAll();

        assertEquals(false, materialList.isEmpty());

    }

    @Test
    @Transactional
    public void findByUser_Id() throws Exception {

        // BUILD
        String ID = "1";

        // OPERATE
        List<Material> materialList2 = materialService.findByUserId(ID);

        // CHECK
        DisplayData.output(materialList2);
        assertEquals(false, materialList2.isEmpty());

    }


    @Test
    @Transactional
    public void testFindById() throws Exception {

        // BUILD
        final Integer ID = 1;

        // OPERATE
        Optional<Material> materialOptional = materialService.findById(ID);

        // CHECK
        assertEquals(false, materialOptional.get().getSlideList().isEmpty());

    }

    @Test
    public void testSave() throws Exception{

        //Create Material
        String fileName = "PDFsam_merge.pdf";

        //Create Material
        Material material = new Material();
        material.setText("Material title");

        //Some tests
        assertEquals(true,materialService.save(material).isPresent());
    }


    @Test
    public void testSize() throws Exception {

        final long SIZE = 3;

        assertEquals(OptionalLong.of(SIZE),materialService.size());

    }

}
