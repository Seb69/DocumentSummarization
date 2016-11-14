package com.kyushu.autosum.servicelayer.selection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.repositorylayer.generators.GenerateSlide;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

/**
 * Tester : SelectionSlidesImpl
 *
 * @author ANDRE
 * @since 16/06/16
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectionSlidesImpl_Test {

    @Spy
    SelectionSlides selectionSlidesSpy;

    @Mock
    Material materialMock;

    @Test
    public void selectionSlides_Material__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();
        List<Slide> slideList = material.getSlideList();
        slideList.get(0).setGlobalScore(Double.valueOf(3));
        slideList.get(1).setGlobalScore(Double.valueOf(2));
        slideList.get(2).setGlobalScore(Double.valueOf(1));
        DisplayData.input(material);

        // STUB
        doReturn(slideList).when(selectionSlidesSpy).optimizationTime(anyInt());

        // OPERATE
        material = selectionSlidesSpy.selectionSlides(material, 60);

        // CHECK
        DisplayData.output(material);
        assertEquals(1, material.getSlideList().get(0).getGlobalScore().compareTo(material.getSlideList().get(1).getGlobalScore()));

    }

    @Test
    public void optimizationTime_Material__MaterialWithSlideSelected() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();
        List<Slide> slideList = material.getSlideList();
        slideList.get(0).setTime(10);
        slideList.get(1).setTime(30);
        slideList.get(2).setTime(80);
        DisplayData.input(material);

        // STUB
        Mockito.when(materialMock.getSlideList()).thenReturn(material.getSlideList());

        // OPERATE
        List<Slide> slideList1 = selectionSlides.optimizationTime(Integer.valueOf(40));

        // CHECK
        DisplayData.output(slideList1);
        assertEquals(2, slideList1.stream().filter(slide -> slide.getSelected()).count());

    }

    @InjectMocks
    SelectionSlides selectionSlides = new SelectionSlides();


}
