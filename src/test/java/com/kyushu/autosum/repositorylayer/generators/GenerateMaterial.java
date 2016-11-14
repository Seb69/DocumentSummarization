package com.kyushu.autosum.repositorylayer.generators;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;


/**
 * @author ANDRE
 * @since 16/05/16
 */
public class GenerateMaterial {

    public static Material createEnglish() {

        Material material = new Material();
        material.setText("This This is the first first slide This is the second second slide new version of slide");
        List<String> stringListMaterial = Arrays.asList("This","This", "is", "the", "first","first", "slide", "This", "is", "the", "second","second", "slide","new", "version", "of", "slide");
        material.setMaterialFilePATH("src/main/java/com/kyushu/autosum/upload-dir/4cmU/4cmU-Presentation1.pdf");
        material.setKeywordsList(stringListMaterial);

        Slide slide1 = new Slide();
        slide1.setSLIDE_ID(11);
        slide1.setText("This This is the first first slide");
        slide1.setGlobalScore(Double.valueOf(3));
        List<String> stringList1 = Arrays.asList("This","This", "is", "the","first", "first", "slide");
        slide1.setKeywordsList(stringList1);

        Slide slide2 = new Slide();
        slide2.setSLIDE_ID(12);
        slide2.setText("This is the second second slide");
        slide2.setGlobalScore(Double.valueOf(4));
        List<String> stringList2 = Arrays.asList("This", "is", "the", "second","second", "slide");
        slide2.setKeywordsList(stringList2);

        Slide slide3 = new Slide();
        slide3.setSLIDE_ID(13);
        slide3.setText("new version of slide");
        slide3.setGlobalScore(Double.valueOf(6));
        List<String> stringList3 = Arrays.asList("new", "version", "of", "slide");
        slide3.setKeywordsList(stringList3);

        material.addSlide(slide1);
        material.addSlide(slide2);
        material.addSlide(slide3);

        return material;

    }

    public static Material createEnglishPartialScore() {
        Material material = createEnglish();
        material.getSlideList().get(0).setTextScore(Double.valueOf(421));
        material.getSlideList().get(0).setInterFrameScore(Double.valueOf(422));
        material.getSlideList().get(0).setBackgroundScore(Double.valueOf(422));

        material.getSlideList().get(1).setTextScore(Double.valueOf(423));
        material.getSlideList().get(1).setInterFrameScore(Double.valueOf(424));
        material.getSlideList().get(1).setBackgroundScore(Double.valueOf(425));

        material.getSlideList().get(2).setTextScore(Double.valueOf(426));
        material.getSlideList().get(2).setInterFrameScore(Double.valueOf(427));
        material.getSlideList().get(2).setBackgroundScore(Double.valueOf(428));

        return material;

    }

    public static Material createEnglish_Input() {

        Material material = createEnglish();

        DisplayData.input(material);

        return material;

    }

    public static Material createJapaneses() {

        Material material = new Material();
        material.setText("デジタル画像をコンピュータで「処理」すること");
        List<String> stringListMaterial = Arrays.asList("デジ", "タル", "画像", "像をコ", "タで", "画像", "する", "こと");
        material.setKeywordsList(stringListMaterial);

        List<Slide> slideList = new ArrayList<>();

        Slide slide = new Slide();
        slide.setText("デジタル画像をコンピュータで");
        List<String> stringList1 = Arrays.asList("デジ", "タル",  "像をコ", "タで");
        slide.setKeywordsList(stringList1);
        slideList.add(slide);

        Slide slide2 = new Slide();
        slide2.setText("「処理」すること");
        List<String> stringList2 = Arrays.asList("画像","画像","画像", "する", "こと");
        slide2.setKeywordsList(stringList2);
        slideList.add(slide2);
        material.setSlideList(slideList);

        return material;

    }
    public static Material createJapaneses_Input() {

        Material material = createJapaneses();

        DisplayData.input(material);

        return material;

    }

    public static Material createJapanesesEmptyKeywordList() {

        Material material = new Material();
        material.setText("デジタル画像をコンピュータで「処理」すること");

        Slide slide = new Slide();
        slide.setText("デジタル画像をコンピュータで");
        material.addSlide(slide);

        Slide slide2 = new Slide();
        slide2.setText("「処理」すること");
        material.addSlide(slide2);

        DisplayData.input(material);

        return material;

    }

    public static Material createJapanesesEmptyKeywordList_Input() {

        Material material = createJapanesesEmptyKeywordList();

        DisplayData.input(material);

        return material;

    }

    public static Material createEnglishEmptyKeywordList() {

        Material material = new Material();
        material.setText("This is the first slide This is the second slide");

        Slide slide1 = new Slide();
        slide1.setText("This is the first slide");

        Slide slide2 = new Slide();
        slide2.setText("This is the second slide");

        material.addSlide(slide1);
        material.addSlide(slide2);

        return material;

    }
    public static Material createEnglishEmptyKeywordList_Input() {

        Material material = createEnglishEmptyKeywordList();

        DisplayData.input(material);

        return material;

    }


}
