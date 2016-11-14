package com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.saliency;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

/**
 * Tester : SaliencyProcessingImpl
 *
 * @author ANDRE
 * @since 05/07/16
 */
@RunWith(MockitoJUnitRunner.class)
public class SaliencyProcessingImpl_Test {

    @Test
    public void saliencyProcessing() throws Exception {

        // BUILD
        File inputFile = GenerateFile.createJPG_4();

        // OPERATE
        saliencyProcessingImpl.SaliencyProcessing(inputFile);

        // CHECK


    }

    @InjectMocks
    SaliencyProcessingImpl saliencyProcessingImpl = new SaliencyProcessingImpl();


}
