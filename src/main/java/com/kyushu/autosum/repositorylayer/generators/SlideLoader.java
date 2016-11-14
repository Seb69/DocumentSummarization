package com.kyushu.autosum.repositorylayer.generators;

import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.repositoryservice.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ANDRE on 19/04/16.
 */
@Component
public class SlideLoader {

    private SlideService slideService;

    @Autowired
    public void setSlideService(SlideService slideService) {
        this.slideService = slideService;
    }

    public void slideLoader() {

        Slide slide1 = new Slide();
        slide1.setText("FIRST SLIDE");
        slide1.setBackgroundScore(Double.valueOf(10));
        slide1.setInterFrameScore(Double.valueOf(6));
        slide1.setTextScore(Double.valueOf(12));
        slide1.setSaliencyMapScore(Double.valueOf(14));
        slide1.setGlobalScore(Double.valueOf(37));
        slideService.save(slide1);

        Slide slide2 = new Slide();
        slide2.setText("SECOND SLIDE");
        slide2.setInterFrameScore(Double.valueOf(10));
        slide2.setBackgroundScore(Double.valueOf(7));
        slide2.setSaliencyMapScore(Double.valueOf(4));
        slide2.setTextScore(Double.valueOf(14));
        slide2.setGlobalScore(Double.valueOf((56)));
        slideService.save(slide2);

    }

}
