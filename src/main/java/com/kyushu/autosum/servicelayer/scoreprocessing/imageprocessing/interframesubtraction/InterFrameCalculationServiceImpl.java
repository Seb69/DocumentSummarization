package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ANDRE
 * @since 30/05/16
 */
@Service
public class InterFrameCalculationServiceImpl implements InterFrameCalculationService {

    private static final Logger LOG = LoggerFactory.getLogger(InterFrameCalculationServiceImpl.class);

    private int HEIGHT ;

    private int WIDTH ;

    private InterFrameSubtraction interFrameSubtraction;

    @Autowired
    public void setInterFrameSubtraction(InterFrameSubtraction interFrameSubtraction) {
        this.interFrameSubtraction = interFrameSubtraction;
    }

    /**
     * Calculate background score
     *
     * @param bufferedImageList to calculate
     * @return the result calculation score
     */
    public Map<Integer, Double> calculateInterFrameScore(List<BufferedImage> bufferedImageList) {

        HEIGHT = bufferedImageList.get(0).getHeight();
        WIDTH = bufferedImageList.get(0).getWidth();

        Map<Integer, Double> map = new HashMap<>();

        // Test if there are at least 2 slide
        if (bufferedImageList.size() < 2){
            map.put(0, Double.valueOf(0));
            return map;
        }

        // First Slide
        Long scoreFirstSlide = interFrameSubtraction.calculationInterFrameSubtraction(bufferedImageList.get(0), bufferedImageList.get(1));
        map.put(0, Double.valueOf(scoreFirstSlide));

        int i;
        for ( i = 1; i < bufferedImageList.size() - 1; i++) {

            Long scorePrevious = interFrameSubtraction.calculationInterFrameSubtraction(bufferedImageList.get(i - 1), bufferedImageList.get(i));
            Long scoreNext = interFrameSubtraction.calculationInterFrameSubtraction(bufferedImageList.get(i), bufferedImageList.get(i + 1));

            Double maximum = Double.valueOf(Math.max(scorePrevious, scoreNext));

            map.put(i, maximum);
        }

        // Last Slide
        Long scoreLastSlide = interFrameSubtraction.calculationInterFrameSubtraction(bufferedImageList.get(i - 1), bufferedImageList.get(i));
        map.put(bufferedImageList.size()-1, Double.valueOf(scoreLastSlide));

        return interFrameFactor(map);
    }

    /**
     * Calculate the number of white pixels
     *
     * @param map to calculate factor
     * @return the number of white pixels
     */
    Map<Integer, Double> interFrameFactor(Map<Integer, Double> map) {

        Map<Integer, Double>  factor = map.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> (e.getValue()* 15) / Double.valueOf(HEIGHT * WIDTH)
                ));

        return factor;
    }


}
