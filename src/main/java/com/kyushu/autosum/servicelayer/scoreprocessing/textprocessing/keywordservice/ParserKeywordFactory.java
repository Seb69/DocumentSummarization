package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ANDRE
 * @since 14/05/16
 */
@Service
public class ParserKeywordFactory {

    private LanguageDetector languageDetector;

    private JapaneseKeywordParser japaneseKeywordParser;

    private EnglishKeywordParser englishKeywordParser;

    @Autowired
    public void setLanguageDetector(LanguageDetector languageDetector) {
        this.languageDetector = languageDetector;
    }

    @Autowired
    public void setJapaneseKeywordParser(JapaneseKeywordParser japaneseKeywordParser) {
        this.japaneseKeywordParser = japaneseKeywordParser;
    }

    @Autowired
    public void setEnglishKeywordParser(EnglishKeywordParser englishKeywordParser) {
        this.englishKeywordParser = englishKeywordParser;
    }

    /**
     * Get the right parserKeyword (english or japanese)
     *
     * @param text to detect which parser fit the best
     * @return the suitable parser
     */
    public KeywordParser getKeywordParser(String text) {

        String lang = languageDetector.detectLanguage(text);

        switch (lang) {

            case "en":
                return englishKeywordParser;

            case "ja":
                return japaneseKeywordParser;

            default:
                return japaneseKeywordParser;
        }

    }
}
