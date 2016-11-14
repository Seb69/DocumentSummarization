package com.kyushu.autosum.servicelayer.scoreprocessing.timeprocessing;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ANDRE
 * @since 04/06/16
 */
@Service
public class TimeProcessing {

    private static final Logger LOG = LoggerFactory.getLogger(TimeProcessing.class);


    /**
     * Here we estimate the time according to the size of text and image content
     * @param material
     * @return
     */
    public Material calculateTime(Material material) {

        material.getSlideList().stream()
                .forEach(e -> e.setTime(e.getKeywordsList().size() + e.getBackgroundScore().intValue()));

        return material;

    }

//
//    List<Integer> retrieveTime(Material material) {
//
//        List<Slide> slideList = material.getSlideList();
//
//        int size = slideList.size()-1;
//
//        List<Integer> integerList = new ArrayList<>();
//
//        for (int i = 0; i < size; i++) {
//            integerList.add(10);
//        }
//
//        return integerList;
//    }


}
