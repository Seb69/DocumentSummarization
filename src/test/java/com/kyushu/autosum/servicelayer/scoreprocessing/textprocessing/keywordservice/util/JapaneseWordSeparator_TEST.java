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
 * @since 14/05/16
 */
@RunWith(PowerMockRunner.class)
public class JapaneseWordSeparator_TEST {

    @InjectMocks
    JapaneseWordSeparator japaneseWordSeparator = new JapaneseWordSeparator();

    @Test
    public void parseJapansesKeyword_String__StringList() throws Exception {

        // BUILD
        String string = "デジタル画像をコンピュータで「処理」すること。";

        // OPERATE
        List<String> stringList = japaneseWordSeparator.parseJapaneseString(string);

        // CHECK
        assertEquals(false, stringList.isEmpty());
        DisplayData.output(stringList);

    }
}
