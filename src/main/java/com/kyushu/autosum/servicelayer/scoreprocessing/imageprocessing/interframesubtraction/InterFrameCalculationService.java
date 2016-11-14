package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 * @author ANDRE
 * @since 30/05/16
 */
public interface InterFrameCalculationService {
    Map<Integer, Double> calculateInterFrameScore(List<BufferedImage> bufferedImageList);
}
