package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ANDRE
 * @since 30/05/16
 */
@Service
public class BackgroundCalculationServiceImpl implements BackgroundCalculationService {

    private static final Logger LOG = LoggerFactory.getLogger(BackgroundCalculationServiceImpl.class);

    private BackgroundSubtraction backgroundSubtraction;

    private int HEIGHT;

    private int WIDTH;

    @Autowired
    public void setBackgroundSubtraction(BackgroundSubtraction backgroundSubtraction) {
        this.backgroundSubtraction = backgroundSubtraction;
    }

    /**
     * Calculate background score
     * @param bufferedImageList to calculate
     * @return the result calculation score
     */
    public List<Double> calculateBackgroundScore(List<BufferedImage> bufferedImageList) {

        HEIGHT = bufferedImageList.get(0).getHeight();
        WIDTH = bufferedImageList.get(0).getWidth();

        List<Long> longs = bufferedImageList.stream()
                .map(backgroundSubtraction::calculationBackSubtraction)
                .collect(Collectors.toList());

        return imageProcessingFactor(longs);
    }

    /**
     * Calculate the number of white pixels
     * @param whitesPixelsList list of binary pixels
     * @return the number of white pixels
     */
    List<Double> imageProcessingFactor(List<Long> whitesPixelsList) {

        List<Double> longs = whitesPixelsList.stream()
                .map(wPixels -> (wPixels * 15) / Double.valueOf(WIDTH * HEIGHT))
                .collect(Collectors.toList());

        return longs;
    }



}
