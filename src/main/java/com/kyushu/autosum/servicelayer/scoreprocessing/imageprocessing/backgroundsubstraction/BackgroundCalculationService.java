package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author ANDRE
 * @since 30/05/16
 */
public interface BackgroundCalculationService {
    List<Double> calculateBackgroundScore(List<BufferedImage> bufferedImageList);
}
