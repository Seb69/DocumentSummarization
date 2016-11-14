package com.kyushu.autosum.servicelayer.scoreprocessing;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.Test;
import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.mockito.Matchers.any;

/**
 * Tester : SlideScoreCalculation
 *
 * @author ANDRE
 * @since 04/06/16
 */
public class SlideScoreCalculation_IT extends AbstractIntegrationTest {

    private SlideScoreCalculation slideScoreCalculation;

    @Autowired
    public void setSlideScoreCalculation(SlideScoreCalculation slideScoreCalculation) {
        this.slideScoreCalculation = slideScoreCalculation;
    }


    @Test
    public void calculateSlideScore() throws Exception {

        // BUILD
        File fileUploaded = GenerateFile.createPPT_Server();

        // OPERATE
        Material material = slideScoreCalculation.calculateSlideScore(fileUploaded);

        // CHECK
        DisplayData.output(material);

    }


    @Test
    public void globalSlideScore_Material__Material() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();

        // OPERATE
        slideScoreCalculation.globalSlideScore(material.getSlideList());

        // CHECK
        DisplayData.output(material);

    }


}
    