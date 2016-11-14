package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.repositorylayer.domain.Material;

/**
 * @author ANDRE
 * @since 15/05/16
 */
public interface ParseMaterialKeywordService {
    Material parseMaterialKeyword(Material material);
}
