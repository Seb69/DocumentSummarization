package com.kyushu.autosum.servicelayer.scoreprocessing;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.ImageProcessingCalculator;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation.SlideWeightServiceImpl;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.ParseMaterialKeywordServiceImpl;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices.ParseMaterialServiceImpl;
import com.kyushu.autosum.servicelayer.scoreprocessing.timeprocessing.TimeProcessing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.util.logging.Logger.global;

/**
 * @author ANDRE
 * @since 04/06/16
 */
@Service
public class SlideScoreCalculation {

    private static final Logger LOG = LoggerFactory.getLogger(SlideScoreCalculation.class);

    private Material material;

    private ParseMaterialKeywordServiceImpl parseMaterialKeywordService;

    private ParseMaterialServiceImpl parseMaterialService;

    private SlideWeightServiceImpl slideWeightService;

    private ImageProcessingCalculator imageProcessingCalculator;

    private TimeProcessing timeProcessing;

    @Autowired
    public void setParseMaterialKeywordService(ParseMaterialKeywordServiceImpl parseMaterialKeywordService) {
        this.parseMaterialKeywordService = parseMaterialKeywordService;
    }

    @Autowired
    public void setParseMaterialService(ParseMaterialServiceImpl parseMaterialService) {
        this.parseMaterialService = parseMaterialService;
    }

    @Autowired
    public void setSlideWeightService(SlideWeightServiceImpl slideWeightService) {
        this.slideWeightService = slideWeightService;
    }

    @Autowired
    public void setImageProcessingCalculator(ImageProcessingCalculator imageProcessingCalculator) {
        this.imageProcessingCalculator = imageProcessingCalculator;
    }

    @Autowired
    public void setTimeProcessing(TimeProcessing timeProcessing) {
        this.timeProcessing = timeProcessing;
    }

    public Material calculateSlideScore(File fileUploaded) {

        // Parse PDF : extract text
        material = parseMaterialService.parseMaterial(fileUploaded);

        // Clean up the text and get keywords list
        material = parseMaterialKeywordService.parseMaterialKeyword(material);

        // Calculate the slide text score
        material = slideWeightService.calculateSlideWeight(material);

        // Calculate Background and interFrame score
        material = imageProcessingCalculator.calculateImageProcessingFactors(fileUploaded, material);

        // Simulate Time
        material = timeProcessing.calculateTime(material);

        globalSlideScore(material.getSlideList());

        return material;
    }

    void globalSlideScore(List<Slide> slideList) {

        List<Double> scoreList = slideList.stream()
                .map(
                        e -> e.getTextScore() + e.getBackgroundScore() + e.getInterFrameScore()
                )
                .collect(Collectors.toList());

        setGlobalScore(scoreList);

    }

    void setGlobalScore(List<Double> scoreList) {

        for (int i = 0; i < material.getSlideList().size(); i++) {
            material.getSlideList().get(i).setGlobalScore(scoreList.get(i));
        }
    }

}
