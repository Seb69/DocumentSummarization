package com.kyushu.autosum.repositorylayer.repositoryservice;

import com.kyushu.autosum.Application;
import com.kyushu.autosum.repositorylayer.repositoryservice.SlideService;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

import java.util.OptionalLong;

/**
 * Created by ANDRE on 19/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SuppressWarnings("Duplicates")
@WebAppConfiguration
public class SlideRepositoryIT {

    private SlideService slideService;

    @Autowired
    public void setSlideService(SlideService slideService) {
        this.slideService = slideService;
    }

    @Test
    public void testFindAll() throws Exception{

        assertEquals(false, slideService.findAll().isEmpty());

    }

    @Test
    public void testFindById() throws Exception{

        final Integer ID = 1;

        assertEquals(true, slideService.findById(ID).isPresent());

    }


    @Test
    public void testSave() throws Exception{

        Slide slide = new Slide();

        assertEquals(true,slideService.save(slide).isPresent());

    }

    @Test
    public void testSize() throws Exception {

        final long SIZE = 7;

        assertEquals(OptionalLong.of(SIZE),slideService.size());

    }
}
