package com.kyushu.autosum.repositorylayer.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by ANDRE on 19/04/16.
 */
@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private MaterialLoader materialLoader;

    private SlideLoader slideLoader;

    @Autowired
    public void setMaterialLoader(MaterialLoader materialLoader) {
        this.materialLoader = materialLoader;
    }

    @Autowired
    public void setSlideLoader(SlideLoader slideLoader) {
        this.slideLoader = slideLoader;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        materialLoader.loadMaterial();
        slideLoader.slideLoader();
    }


}
