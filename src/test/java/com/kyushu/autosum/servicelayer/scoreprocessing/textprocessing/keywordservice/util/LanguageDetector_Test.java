package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @since 13/05/16
 */
@RunWith(PowerMockRunner.class)
public class LanguageDetector_Test {

    @InjectMocks
    LanguageDetector languageDetector = new LanguageDetector();

    @Test
    public void languageDetection_StringEN__StringEN() throws Exception {

        // BUILD
        String content = "presentation of the";

        // OPERATE
        String language = languageDetector.detectLanguage(content);

        // CHECK
        assertEquals("en", language);

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

}
