package com.kyushu.autosum.servicelayer.convertorservice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author ANDRE
 * @since 09/05/16
 */
public interface ConvertToImageService {
    List<BufferedImage> ConvertToImage(File file);
}
