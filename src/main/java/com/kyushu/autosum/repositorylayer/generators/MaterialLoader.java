package com.kyushu.autosum.repositorylayer.generators;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ANDRE on 12/9/15.
 */
@Component
public class MaterialLoader {

    private MaterialService materialService;

    @Autowired
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }


    public void loadMaterial()  {


        Material material = new Material();
        material.setId(1);
        material.setText("This This is the first first slide This is the second second slide new version of slide");
        List<String> stringListMaterial = Arrays.asList("This","This", "is", "the", "first","first", "slide", "This", "is", "the", "second","second", "slide","new", "version", "of", "slide");
        material.setMaterialFilePATH("AutoSumAPI_V3/src/main/java/com/kyushu/autosum/upload-dir/4cmU/4cmU-Presentation1.pdf");
        material.setKeywordsList(stringListMaterial);

        Slide slide11 = new Slide();
        slide11.setText("This This is the first first slide");
        List<String> stringList11 = Arrays.asList("This","This", "is", "the","first", "first", "slide");
        slide11.setTime(60);
        slide11.setGlobalScore(Double.valueOf(1));
        slide11.setKeywordsList(stringList11);

        Slide slide21 = new Slide();
        slide21.setText("This is the second second slide");
        List<String> stringList21 = Arrays.asList("This", "is", "the", "second","second", "slide");
        slide21.setTime(60);
        slide21.setGlobalScore(Double.valueOf(5));
        slide21.setKeywordsList(stringList21);

        Slide slide31 = new Slide();
        slide31.setText("new version of slide");
        List<String> stringList31 = Arrays.asList("new", "version", "of", "slide");
        slide31.setTime(60);
        slide31.setGlobalScore(Double.valueOf(3));
        slide31.setKeywordsList(stringList31);

        material.addSlide(slide11);
        material.addSlide(slide21);
        material.addSlide(slide31);

        material.setUserId(String.valueOf(1));

        materialService.save(material);


    }
}
