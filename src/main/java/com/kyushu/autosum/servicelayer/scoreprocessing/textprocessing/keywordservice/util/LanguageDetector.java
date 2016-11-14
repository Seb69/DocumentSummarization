package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Detect the natural language of the document
 *
 * @author ANDRE
 * @since 13/05/16
 */
@Component
public class LanguageDetector {

    private static final Logger LOG = LoggerFactory.getLogger(LanguageDetector.class);

    /**
     * Detection of the language
     *
     * @param content is a string
     * @return the language under ISO code
     */
    public String detectLanguage(String content) {

        DetectorLoader.getInstance();
        Detector detector = getDetectorFactory();

        detector.append(content);

        return detect(detector);

    }

    /**
     * Handle LangDetectException
     *
     * @param detector to detect
     * @return the code ISO of language detect
     */
    String detect(Detector detector) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(detector.detect());
        } catch (LangDetectException e) {
            LOG.error(e.getMessage());
        }
        return stringBuilder.toString();
    }

    /**
     * Handle LangDetectException
     *
     * @return the detector created
     */
    Detector getDetectorFactory() {
        Detector detector = null;
        try {
            detector = DetectorFactory.create();
        } catch (LangDetectException e) {
            LOG.error(e.getMessage());
        }
        return detector;
    }

}
