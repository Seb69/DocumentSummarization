package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.saliency;

import boofcv.struct.image.GrayF32;

import java.io.File;
import java.io.IOException;

/**
 * @author ANDRE
 * @since 05/07/16
 */
public interface SaliencyProcessing {
    /**
     * This method is processing Saliency method
     * @param file to process
     * @throws IOException
     * @throws InterruptedException
     */
    void SaliencyProcessing(File file) throws IOException, InterruptedException;

}
