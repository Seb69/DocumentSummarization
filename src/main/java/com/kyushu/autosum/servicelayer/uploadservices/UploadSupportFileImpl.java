package com.kyushu.autosum.servicelayer.uploadservices;

import com.kyushu.autosum.servicelayer.uploadservices.exceptions.FileTypeNotDetect;
import com.kyushu.autosum.servicelayer.util.DetectMimeType;
import com.kyushu.autosum.servicelayer.util.enumeration.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Class which test if the file is supported by application
 *
 * @author ANDRE
 * @since 10/05/16
 */
@Service
public class UploadSupportFileImpl implements UploadSupportFile {

    private static final Logger LOG = LoggerFactory.getLogger(UploadSupportFileImpl.class);

    /**
     * Test if the file is supported by application
     *
     * @param file to test
     * @return true if file is supported, false if not supported
     */
    @Override
    public boolean isSupport(File file) {

        String contentType = getMimeType(file);

        switch (contentType) {

            case MimeType.PDF:
                return true;
            case MimeType.PPT:
                return true;

            case MimeType.PPTX:
                return true;

            default:
                return false;
        }
    }

    /**
     * Handle FileTypeNotDetect
     *
     * @param file to get mime type
     * @return the mime type as string
     */
    String getMimeType(File file) {

        StringBuilder mimeType = new StringBuilder();
        try {
            mimeType.append(DetectMimeType.getMimeType(file));
        } catch (FileTypeNotDetect fileTypeNotDetect) {
            LOG.error(fileTypeNotDetect.getMessage());
        }
        return mimeType.toString();
    }

}
