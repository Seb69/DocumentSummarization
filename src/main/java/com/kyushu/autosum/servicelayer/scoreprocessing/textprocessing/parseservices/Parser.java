package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import java.io.File;
import java.util.List;

/**
 * Interface
 */
public interface Parser {
    String parseFullText(File file);

    List<String> parseSlideText(File file);
}
