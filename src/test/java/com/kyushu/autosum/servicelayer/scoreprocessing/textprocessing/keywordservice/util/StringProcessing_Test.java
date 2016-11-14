package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 16/05/16
 */
@RunWith(PowerMockRunner.class)
public class StringProcessing_Test {

    @InjectMocks
    StringProcessing stringProcessing = new StringProcessing();

    @Test
    public void parseString_StringEn__StringList() throws Exception {

        // BUILD
        String string = "Hello World !   () f    gdfsd  57";

        // OPERATE
        List<String> stringList = stringProcessing.parseString(string);

        // CHECK
        DisplayData.output(stringList);
        assertEquals(false, stringList.isEmpty());

    }

    @Test
    public void testCleanString_StringEN__String() throws Exception {

        // BUILD
        String string = "HELLO67 78 YHHJSIbsghu6vjhs7bijs ! %:)!§('& ,. . ? $ * ";

        // OPERATE
        String cleanedString = stringProcessing.cleanString(string);

        // CHECK
        DisplayData.output(cleanedString);
        assertEquals(false, cleanedString.isEmpty());

    }

    @Test
    public void testCleanString_StringJP__String() throws Exception {

        // BUILD
        String string = "デジタル画像をコンピュータで ( ) ( ) 「処理」すること";

        // OPERATE
        String cleanedString = stringProcessing.cleanString(string);

        // CHECK
        DisplayData.output(cleanedString);
        assertEquals(false, cleanedString.isEmpty());

    }
}
