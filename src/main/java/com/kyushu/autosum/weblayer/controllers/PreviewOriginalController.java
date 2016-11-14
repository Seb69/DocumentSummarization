package com.kyushu.autosum.weblayer.controllers;

import com.kyushu.autosum.servicelayer.selection.views.ImagesMetadataPreview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * @author ANDRE
 * @since 17/08/16
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PreviewOriginalController {

    private static final Logger LOG = LoggerFactory.getLogger(PreviewOriginalController.class);

    private ImagesMetadataPreview imagesMetadataPreview;

    @Autowired
    public void setImagesMetadataPreview(ImagesMetadataPreview imagesMetadataPreview) {
        this.imagesMetadataPreview = imagesMetadataPreview;
    }


    @RequestMapping(
            value = "/materialPreview/{id}/{slide}",
            method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource materialPreviewOriginal(@PathVariable("id") Integer id, @PathVariable("slide") Integer slide_id ) {

        long START_TIME = System.currentTimeMillis();

        List<String> links = imagesMetadataPreview.getMaterialPreviewLink(id);

        long END_TIME = System.currentTimeMillis() - START_TIME;
        LOG.info("GET : /materialPreview/ finished in: " + END_TIME + " milliseconds");

        return new FileSystemResource(links.get(slide_id));
    }

    @RequestMapping(
            value = "/getMaterialPreviewLink/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public List<String> getMaterialPreviewLink(@PathVariable("id") Integer id ) {

        long START_TIME = System.currentTimeMillis();

        List<String> links = imagesMetadataPreview.getMaterialPreviewLink(id);

        imagesMetadataPreview.originalImage(id);

        links.stream().forEach(System.out::println);

        long END_TIME = System.currentTimeMillis() - START_TIME;
        LOG.info("GET : /materialPreviewOriginal/" + id + " finished in: " + END_TIME + " milliseconds");

        return links;
    }

    @RequestMapping(
            value = "/materialPreviewOriginalMetadata/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public int materialPreviewOriginalMetadata(@PathVariable("id") Integer id ) {

        long START_TIME = System.currentTimeMillis();

        int size = imagesMetadataPreview.countNumberOriginalImage(id);

        long END_TIME = System.currentTimeMillis() - START_TIME;
        LOG.info("GET : /materialPreviewOriginalMetadata/" + id + " finished in: " + END_TIME + " milliseconds");

        return size;
    }

}
