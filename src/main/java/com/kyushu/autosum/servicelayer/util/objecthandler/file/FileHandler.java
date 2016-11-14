package com.kyushu.autosum.servicelayer.util.objecthandler.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static thredds.featurecollection.FeatureCollectionConfig.PartitionType.file;

/**
 * @author ANDRE
 * @since 23/05/16
 */
@Service
public class FileHandler {

    private static final Logger LOG = LoggerFactory.getLogger(FileHandler.class);

    /**
     * Handle IOException
     *
     * @param file to create
     * @return the file created
     */
    public static File createNewFile(File file) {

        try {
            file.createNewFile();
        } catch (IOException e) {
            LOG.error(e.toString());
        }
        return file;
    }

    /**
     * Handle IOException
     *
     * @param file to create
     * @return the file created
     */
    public static FileInputStream createInputStream(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (IOException e) {
            LOG.error(e.toString());
        }
        return fileInputStream;
    }

    /**
     * Handle IOException
     * Close Stream
     */
    public static void close(FileInputStream fileInputStream) {
        try {
            fileInputStream.close();
        } catch (IOException e) {
            LOG.error(e.toString());
        }
    }

}
