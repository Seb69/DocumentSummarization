package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 14/05/16
 */
public class JapaneseWordSeparator_IT extends AbstractIntegrationTest {

    private JapaneseWordSeparator japaneseWordSeparator;

    @Autowired
    public void setJapaneseWordSeparator(JapaneseWordSeparator japaneseWordSeparator) {
        this.japaneseWordSeparator = japaneseWordSeparator;
    }

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
