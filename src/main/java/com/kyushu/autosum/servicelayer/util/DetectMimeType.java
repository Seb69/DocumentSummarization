package com.kyushu.autosum.servicelayer.util;

import com.kyushu.autosum.servicelayer.uploadservices.exceptions.FileTypeNotDetect;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class detect the mime type of file
 *
 * @author ANDRE
 * @since 22/05/16
 */
@Component
public class DetectMimeType {

    private static final Logger LOG = LoggerFactory.getLogger(DetectMimeType.class);

    /**
     * Obtain the Mime type of the file
     *
     * @param file to get mime type
     * @return the mime type as string
     * @throws FileTypeNotDetect if file is empty
     */
    public static String getMimeType(File file) throws FileTypeNotDetect {

        String mimeType = detect(file);

        if (mimeType.equals("octet-stream")) {
            throw new FileTypeNotDetect("Mime type not detected");
        } else {
            return mimeType;
        }
    }

    /**
     * Handle IOException
     *
     * @param file to detect mime Type
     * @return the mime type
     */
    public static String detect(File file) {

        Tika tika = new Tika();

        StringBuilder mimeType = new StringBuilder();
        try {
            mimeType.append(tika.detect(file));
        } catch (FileNotFoundException ex) {
            LOG.error("Error Tika detect" + ex.toString());
        } catch (IOException e) {
            LOG.error(e.toString());
        }
        return mimeType.toString();
    }

}
