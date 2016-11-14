package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.saliency;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Tester : SaliencyProcessingImpl_IT
 *
 * @author ANDRE
 * @since 29/08/16
 */
public class SaliencyProcessingImpl_IT extends AbstractIntegrationTest {

    private SaliencyProcessingImpl saliencyProcessingImpl;

    @Autowired
    public void setSaliencyProcessingImpl_IT(SaliencyProcessingImpl saliencyProcessingImpl) {
        this.saliencyProcessingImpl = saliencyProcessingImpl;
    }

    @Test
    public void saliencyProcessing() throws Exception {

        // BUILD
        File inputFile = GenerateFile.createJPG_4();

        // OPERATE
        saliencyProcessingImpl.SaliencyProcessing(inputFile);

        // CHECK


    }

}
    