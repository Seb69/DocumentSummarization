package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author ANDRE
 * @since 15/05/16
 */
public class ParserKeywordFactory_IT extends AbstractIntegrationTest {

    private ParserKeywordFactory parserKeywordFactory;

    @Autowired
    public void setParserKeywordFactory(ParserKeywordFactory parserKeywordFactory) {
        this.parserKeywordFactory = parserKeywordFactory;
    }


    @Test
    public void getParserKeyword_EN() throws Exception {

        // OPERATE
        KeywordParser keywordParser = parserKeywordFactory.getKeywordParser("hello world how are you ");

        // CHECK
        assertThat(keywordParser ,instanceOf(EnglishKeywordParser.class));

    }

    @Test
    public void getParserKeyword_JP() throws Exception {


        // OPERATE
        KeywordParser keywordParser = parserKeywordFactory.getKeywordParser("デジ");

        // CHECK
        assertThat(keywordParser ,instanceOf(JapaneseKeywordParser.class));

    }
}
