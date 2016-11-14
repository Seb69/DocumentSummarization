package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction.BackgroundCalculationService;
import com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction.InterFrameCalculationService;
import com.kyushu.autosum.servicelayer.convertorservice.ConvertToImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * This class allow the calculation and fill a material object
 * @author ANDRE
 * @since 30/05/16
 */
@Service
public class ImageProcessingCalculator {

    private static final Logger LOG = LoggerFactory.getLogger(ImageProcessingCalculator.class);

    private ConvertToImageService convertToImageService;

    private BackgroundCalculationService backgroundCalculationService;

    private InterFrameCalculationService interFrameCalculationService;

    private  Material material;

    @Autowired
    public void setConvertToImageService(ConvertToImageService convertToImageService) {
        this.convertToImageService = convertToImageService;
    }

    @Autowired
    public void setBackgroundCalculationService(BackgroundCalculationService backgroundCalculationService) {
        this.backgroundCalculationService = backgroundCalculationService;
    }

    @Autowired
    public void setInterFrameCalculationService(InterFrameCalculationService interFrameCalculationService) {
        this.interFrameCalculationService = interFrameCalculationService;
    }

    /**
     * This method calculate the background and inter frame score and fill these into the material object
     * @param file to calculate
     * @param material to fill
     * @return the material with image score filled
     */
    public Material calculateImageProcessingFactors(File file, Material material) {

        this.material = material;

        List<BufferedImage> bufferedImageList = convertToImageService.ConvertToImage(file);

        List<Double> doubleList = backgroundCalculationService.calculateBackgroundScore(bufferedImageList);

        setBackgroundScore(doubleList);

        Map<Integer, Double> map = interFrameCalculationService.calculateInterFrameScore(bufferedImageList);

        setInterFrameScore(map);

        return material;
    }

    /**
     * Set the background score
     * @param doubleList list of slide score
     */
    void setBackgroundScore(List<Double> doubleList) {

        List<Slide> slideList = material.getSlideList();

        for (int i = 0; i < slideList.size(); i++) {
            slideList.get(i).setBackgroundScore(doubleList.get(i));
        }

        setMaterial(material);
        material.setSlideList(slideList);
    }

    /**
     * Set the inter Frame score
     * @param map map of slide and score (slide number, score)
     */
    void setInterFrameScore(Map<Integer, Double> map) {

        List<Slide> slideList = material.getSlideList();

        //loop HashMap
        int i = 0;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            slideList.get(i).setInterFrameScore(entry.getValue());
            i++;
        }

        material.setSlideList(slideList);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
