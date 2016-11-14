package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.generators.GenerateString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 16/05/16
 */
@RunWith(PowerMockRunner.class)
public class TermFrequencyCalculation_Test {

    @InjectMocks
    TermFrequencyCalculation termFrequencyCalculation = new TermFrequencyCalculation();

    @Test
    public void calculateTermFrequency_StringListEnglidh__MapList() throws Exception {

        // BUILD
        List<String> stringList =  GenerateString.createEnglishStringList();

        // OPERATE
        Map<String, Long> mapList = TermFrequencyCalculation.calculateTermFrequency(stringList);

        // CHECK
        DisplayData.output(mapList);
        assertEquals(false , mapList.isEmpty());

    }
    @Test
    public void calculateTermFrequency_StringListJapanese__MapList() throws Exception {

        // BUILD
        List<String> stringList =  GenerateString.createJapaneseStringList();

        // OPERATE
        Map<String, Long> mapList = TermFrequencyCalculation.calculateTermFrequency(stringList);

        // CHECK
        DisplayData.output(mapList);
        assertEquals(false , mapList.isEmpty());

    }
}
