package com.kyushu.autosum.servicelayer.selection;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class allow to select the slide to return to user
 *
 * @author ANDRE
 * @since 16/06/16
 */
@Service
public class SelectionSlides {

    private static final Logger LOG = LoggerFactory.getLogger(SelectionSlides.class);

    private Material material;


    /**
     * This function sorted slide list by weigh
     *
     * @param material to order
     * @return the material sorted
     */
    public Material selectionSlides(Material material, Integer secondes) {

        this.material = material;

        Comparator<Slide> bySlideWeight = (s1, s2) -> Double.compare(s2.getGlobalScore(), s1.getGlobalScore());

        List<Slide> slideList = this.material.getSlideList().stream()
                .sorted(bySlideWeight)
                .collect(Collectors.toList());

        this.material.setSlideList(slideList);


        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.DOWN);

        String numbers = df.format(slideList.size() / 2);

        Integer number = Integer.valueOf(numbers);


        List<Slide> slideListTEMP = this.material.getSlideList();

        for (Slide slide : slideListTEMP) {

            if (number > 0) {
                slide.setSelected(true);
                number = number - 1;
            } else {
                slide.setSelected(false);
            }
        }


        this.material.setSlideList(slideListTEMP);

        return this.material;
    }

    /**
     * Calculate the number of slides that we
     * will return according to the time set up by user.
     * Each slide has a proper time
     *
     * @return the slide list with the field isSelected filled
     */
    List<Slide> optimizationTime(Integer secondes) {

        List<Slide> slideList = this.material.getSlideList();

        LOG.debug("MAX time "+ secondes);

        for (Slide slide : slideList) {
            Integer slideTime = slide.getTime();
            LOG.debug("Slide Time: " + slideTime);
            if (slideTime <= secondes) {
                slide.setSelected(true);
                LOG.debug("Slide n: " + slide.getSLIDE_ID() + " is selected");
                secondes -= slideTime;
                LOG.debug("Time not used: "+ secondes);
            } else slide.setSelected(false);
        }

        return slideList;
    }

}
