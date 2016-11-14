package com.kyushu.autosum.servicelayer.util.objecthandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author ANDRE
 * @since 22/05/16
 */
public class MultipartHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MultipartHandler.class);

    /**
     * Return the contents of the file as an array of bytes.
     *
     * @return the contents of the file as bytes, or an empty byte array if empty
     */
    public static byte[] getBytes(MultipartFile multipartFile) {

        byte[] bytes = null;
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return bytes;
    }

    /**
     * Transfer the received file to the given destination file.
     *
     * @param multipartFile the destination file
     * @param DEST          destination fie
     */
    public static void transferTo(MultipartFile multipartFile, File DEST) {
        try {
            multipartFile.transferTo(DEST);
        } catch (IllegalStateException | IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
