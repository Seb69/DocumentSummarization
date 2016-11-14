package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util.StringProcessing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ANDRE
 * @since 14/05/16
 */
public abstract class KeywordParser {

    abstract List<Slide> parseSlides(List<Slide> slideList);

    /**
     * Parse a material into list of keyword for material and slide text
     *
     * @param material to parse into keyword
     * @return a material with their keyword list
     */
    public Material parseMaterial(Material material) {

        List<Slide> slideList = parseSlides(material.getSlideList());
        material.setSlideList(slideList);

        // Clean material Text
        String materialtext = StringProcessing.cleanString(material.getText());
        material.setText(materialtext);

        return material;

    }

    /**
     * Retrieve all pasre keyword of Slides
     *
     * @param material
     * @return
     */
    public Material concatSlideParsing(Material material) {

        List<Slide> slideList = material.getSlideList();

        List<String> wordsList = slideList.stream()
                .map(Slide::getKeywordsList)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        material.setKeywordsList(wordsList);

        return material;
    }


}
