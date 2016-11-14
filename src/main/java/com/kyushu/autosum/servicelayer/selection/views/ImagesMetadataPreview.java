package com.kyushu.autosum.servicelayer.selection.views;

import java.io.File;
import java.util.List;

/**
 * @author ANDRE
 * @since 17/08/16
 */
public interface ImagesMetadataPreview {
    List<File> originalImage(Integer id);

    int countNumberOriginalImage(Integer id);

    List<String> getMaterialPreviewLink(Integer id);
}

