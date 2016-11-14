package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 13/05/16
 */
public class LanguageDetector_IT extends AbstractIntegrationTest {


    private LanguageDetector languageDetector;

    @Autowired
    public void setLanguageDetector(LanguageDetector languageDetector) {
        this.languageDetector = languageDetector;
    }

    @Test
    public void languageDetection_StringJA__StringJA() throws Exception {

        // BUILD
        String content = "てデジタル画像";

        // OPERATE
        String language = languageDetector.detectLanguage(content);

        // CHECK
        assertEquals("ja", language);

    }

    @Test
    public void languageDetection_StringEN__StringEN() throws Exception {

        // BUILD
        String content = "presentation of the";

        // OPERATE
        String language = languageDetector.detectLanguage(content);

        // CHECK
        assertEquals("en", language);

    }

}
