package com.kyushu.autosum.servicelayer.convertorservice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author ANDRE
 * @since 27/05/16
 */
public interface Converter {
    List<BufferedImage> convert(File file);
}
