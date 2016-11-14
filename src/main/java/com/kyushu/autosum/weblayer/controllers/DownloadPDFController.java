package com.kyushu.autosum.weblayer.controllers;

import com.kyushu.autosum.Application;
import com.kyushu.autosum.servicelayer.selection.DownloadSumFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author ANDRE
 * @since 17/06/16
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@SuppressWarnings("Duplicates")
public class DownloadPDFController {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadPDFController.class);

    private DownloadSumFile downloadSumFile;

    @Autowired
    public void setDownloadSumFile(DownloadSumFile downloadSumFile) {
        this.downloadSumFile = downloadSumFile;
    }

    @RequestMapping(
            value = "/materialPDF/{id}",
            method = RequestMethod.GET,
            produces = "application/pdf")
    @ResponseBody
    public FileSystemResource downloadPDFFile(@PathVariable("id") Integer id) {

        long START_TIME = System.currentTimeMillis();

        File file = downloadSumFile.downloadFile(id);

        String PATH = Application.UploadDir.concat("/" + file.getParentFile().getName()).concat("/" + file.getName());

        long END_TIME = System.currentTimeMillis() - START_TIME;
        LOG.info("POST = /materialPDF/" + id + " finished in: " + END_TIME + " milliseconds");

        return new FileSystemResource(PATH);
    }

    @RequestMapping(
            value = "/materialView/original/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadPDFImage(@PathVariable("id") Integer id) {

        long START_TIME = System.currentTimeMillis();

        File file = downloadSumFile.downloadFile(id);

        LOG.debug(file.getPath());

        String PATH = Application.UploadDir.concat("/" + file.getParentFile().getName()).concat("/Presentation1.pdf" );

        long END_TIME = System.currentTimeMillis() - START_TIME;
        LOG.info("POST = /materialPDF/" + id + " finished in: " + END_TIME + " milliseconds");

        return new FileSystemResource(PATH);
    }

}
