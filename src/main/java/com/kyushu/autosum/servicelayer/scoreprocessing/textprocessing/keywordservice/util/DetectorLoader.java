package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util;

import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ANDRE
 * @since 24/05/16
 */
public class DetectorLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DetectorLoader.class);

    private static DetectorLoader ourInstance = new DetectorLoader();

    private DetectorLoader() {
        try {
            DetectorFactory.loadProfile("AutoSumAPI_V3/src/main/resources/profiles/profiles");
        } catch (LangDetectException e) {
            LOG.error(e.getMessage());
        }

    }


    public static DetectorLoader getInstance() {
        return ourInstance;
    }
}
