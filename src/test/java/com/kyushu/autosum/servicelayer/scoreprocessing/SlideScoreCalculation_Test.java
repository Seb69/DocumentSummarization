package com.kyushu.autosum.servicelayer.scoreprocessing;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.ImageProcessingCalculator;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation.SlideWeightServiceImpl;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.ParseMaterialKeywordServiceImpl;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices.ParseMaterialServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

/**
 * Tester : SlideScoreCalculation
 *
 * @author ANDRE
 * @since 04/06/16
 */
@RunWith(MockitoJUnitRunner.class)
public class SlideScoreCalculation_Test {

    @Mock
    ParseMaterialServiceImpl parseMaterialService;

    @Mock
    ParseMaterialKeywordServiceImpl parseMaterialKeywordService;

    @Mock
    SlideWeightServiceImpl slideWeightService;

    @Mock
    ImageProcessingCalculator imageProcessingCalculator;

    @Spy
    SlideScoreCalculation slideScoreCalculationSpy;

    @Test
    public void calculateSlideScore() throws Exception {

        // BUILD
        File fileUploaded = GenerateFile.createPPT_Server();

        // STUB
        when(parseMaterialService.parseMaterial(fileUploaded)).thenReturn(GenerateMaterial.createEnglish());
        when(parseMaterialKeywordService.parseMaterialKeyword(any(Material.class))).thenReturn(GenerateMaterial.createEnglish());
        when(slideWeightService.calculateSlideWeight(any(Material.class))).thenReturn(GenerateMaterial.createEnglish());
        when(imageProcessingCalculator.calculateImageProcessingFactors(any(File.class),any(Material.class))).thenReturn(GenerateMaterial.createEnglish());

        // OPERATE
        Material material = slideScoreCalculationSpy.calculateSlideScore(fileUploaded);

        // CHECK
        DisplayData.output(material);

    }

//    @Test
//    public void globalSlideScore_Material__Material() throws Exception {
//
//        // BUILD
//        Material material = GenerateMaterial.createEnglishPartialScore();
//
//        // OPERATE
//        material = slideScoreCalculation.globalSlideScore(material);
//
//        // CHECK
//        DisplayData.output(material);
//
//    }

    @InjectMocks
    SlideScoreCalculation slideScoreCalculation = new SlideScoreCalculation();


}
    