package com.kyushu.autosum.servicelayer.scoreprocessing.timeprocessing;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.generators.GenerateMaterial;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tester : TimeProcessing
 *
 * @author ANDRE
 * @since 04/06/16
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeProcessing_Test {
    @Test
    public void calculateTime() throws Exception {

        // BUILD
        Material material = GenerateMaterial.createEnglish();

        // OPERATE
        material = timeProcessing.calculateTime(material);

        // CHECK
        DisplayData.output(material);

    }

    @InjectMocks
    TimeProcessing timeProcessing = new TimeProcessing();


}
    