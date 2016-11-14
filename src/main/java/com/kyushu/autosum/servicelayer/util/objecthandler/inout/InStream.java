package com.kyushu.autosum.servicelayer.util.objecthandler.inout;

import com.kyushu.autosum.servicelayer.util.objecthandler.inout.exception.NoSuchFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author ANDRE
 * @since 28/05/16
 */
public class InStream {


    private static final Logger LOG = LoggerFactory.getLogger(InStream.class);

    /**
     * This method create an fileInputStream and Handle FileNotFoundException
     *
     * @param file to create input file stream
     */
    public static FileInputStream createFileInputStream(File file) {

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new NoSuchFileException("File Input Stream not created", e);
        }

        return fileInputStream;
    }

    /**
     * This method create an Input Stream and Handle IOException
     *
     * @param multipartFile to create input file stream
     */
    public static InputStream createInputStream(MultipartFile multipartFile) {

        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new NoSuchFileException("Input Stream not created", e);
        }
        return inputStream;
    }


    /**
     * This method close all input type stream
     */
    public static <T extends InputStream> void closeInputStream(T stream) {

        try {
            stream.close();
        } catch (NullPointerException | IOException e) {
            LOG.error(e.toString());
        }
    }

}
