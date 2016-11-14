package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Calculate Tf-Idf
 * <p>
 * tf-idf = tf * idf
 * <p>
 * tf = Term Frequency
 * idf = Inverse Document Frequency
 *
 * @author ANDRE
 * @since 18/05/16
 */
public class TermFrequency_InverseDocumentFrequency {

    /**
     * Calculate tf-idf for all slides
     *
     * @param material to calculate
     * @return list of map
     */
    public static List<Map<String, Double>> calculation_TF_IDF(Material material) {

        // Retrieve idf calculation for all material
        Map<String, Double> inverseDocumentFrequency = InverseDocumentFrequency.calculationInverseTermFrequency(material);

        // Retrieve tf calculation for all slides
        List<Map<String, Long>> listSlideKeyword = getTermFrequencyCalculationList(material.getSlideList());

        List<Map<String, Double>> slideList = new ArrayList<>();

        // Calculate TF IDF for each slide
        for (Map<String, Long> slideKeyword : listSlideKeyword) {

            Map<String, Double> slide = slideKeyword.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey(),
                            e -> e.getValue() * inverseDocumentFrequency.entrySet().stream()
                                    .filter(f -> f.getKey().equals(e.getKey()))
                                    .findAny()
                                    .get()
                                    .getValue()
                    ));

            slideList.add(slide);
        }
        return slideList;
    }

    static List<Map<String, Long>> getTermFrequencyCalculationList(List<Slide> slideList) {

        List<Map<String, Long>> listSlideKeyword = slideList.stream()
                .map(slide -> slide.getKeywordsList())
                .map(TermFrequencyCalculation::calculateTermFrequency)
                .collect(Collectors.toList());

        return listSlideKeyword;
    }

}
