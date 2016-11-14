package com.kyushu.autosum.servicelayer.util.objecthandler.inout;

import com.kyushu.autosum.servicelayer.util.objecthandler.inout.exception.NoSuchFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * @author ANDRE
 * @since 28/05/16
 */
public class OutStream {

    private static final Logger LOG = LoggerFactory.getLogger(OutStream.class);

    /**
     * This method create an fileInputStream and Handle FileNotFoundException
     *
     * @param file to create input file stream
     */
    public static FileOutputStream createFileOutputStream(File file) {

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new NoSuchFileException("File output stream not created", e);
        }
        return fileOutputStream;
    }


    /**
     * This method close all output type stream
     */
    public static <T extends OutputStream> void closeOutputStream(T stream) {

        try {
            stream.close();
        } catch (NullPointerException e) {
            throw new NoSuchFileException("File output stream not created", e);
        } catch (IOException e) {
            LOG.error(e.toString());
        }
    }

}
