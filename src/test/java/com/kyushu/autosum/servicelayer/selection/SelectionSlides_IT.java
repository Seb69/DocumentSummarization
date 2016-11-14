package com.kyushu.autosum.servicelayer.selection;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tester : SelectionSlides
 *
 * @author ANDRE
 * @since 18/06/16
 */
public class SelectionSlides_IT extends AbstractIntegrationTest {

    private SelectionSlides selectionSlides;

    @Autowired
    public void setSelectionSlides(SelectionSlides selectionSlides) {
        this.selectionSlides = selectionSlides;
    }


    @Test
    public void selectionSlides_Material__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();
        List<Slide> slideList = material.getSlideList();
        slideList.get(0).setGlobalScore(Double.valueOf(3));
        slideList.get(1).setGlobalScore(Double.valueOf(2));
        slideList.get(2).setGlobalScore(Double.valueOf(1));
        slideList.get(0).setTime(10);
        slideList.get(1).setTime(30);
        slideList.get(2).setTime(80);
        material.setSlideList(slideList);
        DisplayData.input(material);


        // OPERATE
        material = selectionSlides.selectionSlides(material, 60);

        // CHECK
        DisplayData.output(material);
        assertEquals(1, material.getSlideList().get(0).getGlobalScore().compareTo(material.getSlideList().get(1).getGlobalScore()));

    }

//    @Test
//    public void optimizationTime_Material__MaterialWithSlideSelected() throws Exception {
//
//        // BUILD
//        Material material = GenerateMaterial.createEnglish();
//        List<Slide> slideList = material.getSlideList();
//        slideList.get(0).setTime(10);
//        slideList.get(1).setTime(30);
//        slideList.get(2).setTime(80);
//        DisplayData.input(material);
//
//        // OPERATE
//        List<Slide> slideList1 = selectionSlides.optimizationTime(Integer.valueOf(40));
//
//        // CHECK
//        DisplayData.output(slideList1);
//        assertEquals(2, slideList1.stream().filter(slide -> slide.getSelected()).count());
//
//    }

}
