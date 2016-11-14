package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.repositorylayer.domain.Material;

/**
 * @author ANDRE
 * @since 16/05/16
 */
public interface SlideWeightService {
    Material calculateSlideWeight(Material material);
}
