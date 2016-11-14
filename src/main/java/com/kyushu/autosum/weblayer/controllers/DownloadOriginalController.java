package com.kyushu.autosum.weblayer.controllers;

import com.kyushu.autosum.Application;
import com.kyushu.autosum.servicelayer.selection.DownloadOriginalFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author ANDRE
 * @since 16/08/16
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class DownloadOriginalController {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadOriginalController.class);


    private DownloadOriginalFile downloadFile;

    @Autowired
    public void setDownloadFile(DownloadOriginalFile downloadFile) {
        this.downloadFile = downloadFile;
    }

    @RequestMapping(
            value = "/materialOriginal/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadOriginalFile(@PathVariable("id") Integer id) {

        long START_TIME = System.currentTimeMillis();

        File file = downloadFile.downloadOriginalFile(id);

//        String PATH = Application.UploadDir.concat("/" + file.getParentFile().getName()).concat("/" + file.getName());
        String PATH = Application.UploadDir.concat("/" + file.getParentFile().getName()).concat("/" + file.getName());

        long END_TIME = System.currentTimeMillis() - START_TIME;
        LOG.info("GET : /materialOriginal/" + id + " finished in: " + END_TIME + " milliseconds");

        return new FileSystemResource(PATH);
    }

}
