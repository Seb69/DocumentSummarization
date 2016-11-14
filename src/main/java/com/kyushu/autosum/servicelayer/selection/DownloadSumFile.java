package com.kyushu.autosum.servicelayer.selection;

import com.kyushu.autosum.repositorylayer.domain.Material;

import java.io.File;

/**
 * @author ANDRE
 * @since 17/06/16
 */
public interface DownloadSumFile {
    File downloadFile(Integer id);

    Material findMaterial(Integer id);
}
