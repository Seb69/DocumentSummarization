package com.kyushu.autosum.repositorylayer.repositoryservice;

import com.kyushu.autosum.repositorylayer.repositoryservice.SlideServiceImpl;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.repositories.SlideRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import static org.junit.Assert.*;

/**
 * Created by ANDRE on 19/04/16.
 */
@SuppressWarnings("Duplicates")
public class SlideRepositoryTest {

    @Mock
    private SlideRepository slideRepository;

    @InjectMocks
    private SlideServiceImpl slideService = new SlideServiceImpl();

    public SlideRepositoryTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws Exception{

        final long SIZE_LIST = 3;

        List<Slide> slideList = new ArrayList<>();
        slideList.add(new Slide());
        slideList.add(new Slide());

        Mockito.when(slideRepository.findAll()).thenReturn(slideList);

        assertEquals(slideList, slideService.findAll());

    }

    @Test
    public void testFindById() throws Exception{

        final Integer ID = 1;
        final String TEXT_SLIDE = "getSlide";
        long SIZE = 42;

        slideService = Mockito.spy(slideService);

        Mockito.doReturn(OptionalLong.of(SIZE)).when(slideService).size();

        Mockito.when(slideRepository.getOne(ID)).thenReturn(new Slide());

        assertEquals(true, slideService.findById(ID).isPresent());

    }


    @Test
    public void testSave() throws Exception{

        final String TEXT_SLIDE = "setSlide";

        Slide slide = new Slide();
        slide.setText(TEXT_SLIDE);

        Mockito.when(slideRepository.save(slide)).thenReturn(slide);

        assertEquals(true,slideService.save(slide).isPresent());

    }

    @Test
    public void testSize() throws Exception {

        final long SIZE = 2;

        Mockito.when(slideRepository.count()).thenReturn(SIZE);

        assertEquals(OptionalLong.of(SIZE),slideService.size());

    }


}
