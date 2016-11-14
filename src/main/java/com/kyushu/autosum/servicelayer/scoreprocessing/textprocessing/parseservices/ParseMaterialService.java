package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.repositorylayer.domain.Material;

import java.io.File;

/**
 * @author ANDRE
 * @date 03/05/16
 */
public interface ParseMaterialService {
    Material parseMaterial(File file);
}
