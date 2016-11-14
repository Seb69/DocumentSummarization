package com.kyushu.autosum.weblayer.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.*;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import com.kyushu.autosum.servicelayer.scoreprocessing.SlideScoreCalculation;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation.SlideWeightService;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.ParseMaterialKeywordService;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices.ParseMaterialService;
import com.kyushu.autosum.servicelayer.selection.SelectionSlides;
import com.kyushu.autosum.servicelayer.uploadservices.UploadServiceImpl;
import com.kyushu.autosum.weblayer.exceptions.customError.DataNotSaved;
import com.kyushu.autosum.weblayer.exceptions.customError.EmptyMaterialList;
import com.kyushu.autosum.weblayer.exceptions.customError.NoMaterialFoundWithThisId;
import com.kyushu.autosum.weblayer.view.View;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Created by ANDRE on 15/04/16.
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MaterialController {

    private static final Logger LOG = LoggerFactory.getLogger(MaterialController.class);

    private MaterialService materialService;

    private UploadServiceImpl uploadService;

    private ParseMaterialService parseMaterialService;

    private ParseMaterialKeywordService parseMaterialKeywordService;

    private SlideWeightService slideWeightService;

    private SlideScoreCalculation slideScoreCalculation;

    private SelectionSlides selectionSlides;


    @Autowired
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Autowired
    public void setUploadService(UploadServiceImpl uploadService) {
        this.uploadService = uploadService;
    }

    @Autowired
    public void setParseMaterialService(ParseMaterialService parseMaterialService) {
        this.parseMaterialService = parseMaterialService;
    }

    @Autowired
    public void setParseMaterialKeywordService(ParseMaterialKeywordService parseMaterialKeywordService) {
        this.parseMaterialKeywordService = parseMaterialKeywordService;
    }

    @Autowired
    public void setSlideWeightService(SlideWeightService slideWeightService) {
        this.slideWeightService = slideWeightService;
    }

    @Autowired
    public void setSlideScoreCalculation(SlideScoreCalculation slideScoreCalculation) {
        this.slideScoreCalculation = slideScoreCalculation;
    }

    @Autowired
    public void setSelectionSlides(SelectionSlides selectionSlides) {
        this.selectionSlides = selectionSlides;
    }


    /**
     * Post a file and do all treatment
     * @param multipartFile to process
     * @return the material object in JSON format
     */
//    @Cacheable(value = "postMaterial")
    @RequestMapping(
            value = "/materials",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.Material.class)
    public Material postMaterial(@RequestHeader(value="Authorization") String bearer, @RequestPart("file") MultipartFile multipartFile) {

        // Get bearer TOKEN
        bearer = bearer.substring(7);
        Jwt jwt = JwtHelper.decode(bearer);

        // GET THE USER ID
        JsonObject jsonObject = new JsonParser().parse(jwt.getClaims()).getAsJsonObject();
        String user_id = jsonObject.get("user_id").getAsString();

        long START_TIME = System.currentTimeMillis();

        LOG.info(multipartFile.toString());

        // Upload file
        File fileUploaded = uploadService.uploadFile(multipartFile);

        // Calculate the score of each slides
        Material material = slideScoreCalculation.calculateSlideScore(fileUploaded);

        // Set user_id
        material.setUserId(user_id);

        // Select slide
//        selectionSlides.selectionSlides(material, (material.getSlideList().size() / 2) * 60);

        // Save material
        Optional<Material> materialOptional = materialService.save(material);
        // Throw an error if save does not work
        materialOptional.orElseThrow(DataNotSaved::new);

        material = materialOptional.get();

        long END_TIME = System.currentTimeMillis() - START_TIME;
        LOG.info("/materials with file " + multipartFile.getOriginalFilename()  + " finished in: " + END_TIME + " milliseconds");

        return material;

    }
    /**
     * POST MATERIAL for OFFLine mode
     * Post a file and do all treatment
     * @param multipartFile to process
     * @return the material object in JSON format
     */
//    @Cacheable(value = "postMaterial")
    @RequestMapping(
            value = "/materialsOffline",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.Material.class)
    @CrossOrigin(origins = "http://localhost:8080")
    public Material postMaterialOffline( @RequestPart("file") MultipartFile multipartFile) {

        long START_TIME = System.currentTimeMillis();

        LOG.info(multipartFile.toString());

        // Upload file
        File fileUploaded = uploadService.uploadFile(multipartFile);

        // Calculate the score of each slides
        Material material = slideScoreCalculation.calculateSlideScore(fileUploaded);


        // Select slide
//        selectionSlides.selectionSlides(material, (material.getSlideList().size() / 2) * 60);

        // Save material
        Optional<Material> materialOptional = materialService.save(material);
        // Throw an error if save does not work
        materialOptional.orElseThrow(DataNotSaved::new);

        material = materialOptional.get();

        long END_TIME = System.currentTimeMillis() - START_TIME;
        LOG.info("/materials with file " + multipartFile.getOriginalFilename()  + " finished in: " + END_TIME + " milliseconds");

        return material;

    }


    @RequestMapping(
            value = "/materials",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.Material.class)
    public Material putMaterial(@RequestBody Material material) {

        Optional<Material> materialOptional = materialService.update(material);

        materialOptional.orElseThrow(() -> new DataNotSaved());

        return materialOptional.get();
    }


    @RequestMapping(
            value = "/material/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.Material.class)
    public Material getMaterial(@PathVariable("id") Integer id) {

        Optional<Material> materialOptional = materialService.findById(id);

        materialOptional.orElseThrow(() -> new NoMaterialFoundWithThisId(id));

        return materialOptional.get();
    }


    @RequestMapping(
            value = "/materials",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.Material.class)
    public List<Material> getMaterialByUserId(@RequestHeader(value="Authorization") String bearer) {

        // Get bearer TOKEN
        bearer = bearer.substring(7);
        Jwt jwt = JwtHelper.decode(bearer);

        // GET THE USER ID
        JsonObject jsonObject = new JsonParser().parse(jwt.getClaims()).getAsJsonObject();
        String userId = jsonObject.get("user_id").getAsString();


        List<Material> materialList = materialService.findByUserId(String.valueOf(userId));

        return materialList;
    }

}
