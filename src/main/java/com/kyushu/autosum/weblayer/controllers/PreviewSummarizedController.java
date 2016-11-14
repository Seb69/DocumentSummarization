package com.kyushu.autosum.weblayer.controllers;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import com.kyushu.autosum.servicelayer.selection.SelectionSlides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ANDRE
 * @since 23/08/16
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PreviewSummarizedController {

    private static final Logger LOG = LoggerFactory.getLogger(PreviewSummarizedController.class);

    private MaterialService materialService;

    private SelectionSlides selectionSlides;

    @Autowired
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Autowired
    public void setSelectionSlides(SelectionSlides selectionSlides) {
        this.selectionSlides = selectionSlides;
    }

    @RequestMapping(
            value = "/getMaterialPreviewSumLink/{id}",
            method = RequestMethod.GET)
    public List<Integer> getMaterialPreviewSummarizedLink(@PathVariable Integer id) {


        // Retrieve material list
        Optional<Material> materialOptional = materialService.findById(id);
        Material material = materialOptional.get();

        Integer startId = material.getSlideList().get(0).getSLIDE_ID();

        List<Integer> links = new ArrayList<>();

//         retrieve selected slides
        material = selectionSlides.selectionSlides(material, material.getSlideList().size()/2 * 60);


        int size = material.getSlideList().size();


        for (int i = 0; i < size; i++) {

            if (material.getSlideList().get(i).getSelected() == Boolean.TRUE) {
            LOG.debug("Slide n°:" + i + " is selected" + String.valueOf(material.getSlideList().get(i).getSelected()));

                links.add(material.getSlideList().get(i).getSLIDE_ID()-startId);
            }

        }


        // Reorder links in ascanding way
        Comparator<Integer> bySlideId = (s1, s2) -> Integer.compare(s1, s2);

        List<Integer> linksOrdered = links.stream()
                .sorted(bySlideId)
                .collect(Collectors.toList());

        return linksOrdered;

    }


}
