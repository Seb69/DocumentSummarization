package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.repositorylayer.domain.Material;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * This class is calculation the tf-idf score of each slide
 *
 * @author ANDRE
 * @since 16/05/16
 */
@Service
public class SlideWeightServiceImpl implements SlideWeightService {

    /**
     * Calculate the score and and it in material structure
     *
     * @param material to analyse
     * @return material
     */
    @Override
    public Material calculateSlideWeight(Material material) {

        List<Map<String, Double>> tf_idf = TermFrequency_InverseDocumentFrequency.calculation_TF_IDF(material);

        // Go throw list tf-idf
        int count = 0;
        for (Map<String, Double> slide_tf_idf : tf_idf) {

            Double sum = slide_tf_idf.entrySet().stream()
                    .mapToDouble(Map.Entry::getValue)
                    .sum();

            material.getSlideList().get(count).setTextScore(sum);
            count++;
        }

        return material;
    }
}