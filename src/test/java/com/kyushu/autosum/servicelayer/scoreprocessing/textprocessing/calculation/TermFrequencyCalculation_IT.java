package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.generators.GenerateString;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 16/05/16
 */
public class TermFrequencyCalculation_IT extends AbstractIntegrationTest {

    @Test
    public void calculateTermFrequency() throws Exception {

        // BUILD
        List<String> stringList =  GenerateString.createEnglishStringList();

        // OPERATE
        Map<String, Long> mapList = TermFrequencyCalculation.calculateTermFrequency(stringList);

        // CHECK
        DisplayData.output(mapList);
        assertEquals(false , mapList.isEmpty());

    }
}
