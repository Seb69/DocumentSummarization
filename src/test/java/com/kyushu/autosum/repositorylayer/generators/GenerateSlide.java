package com.kyushu.autosum.repositorylayer.generators;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Slide;

import java.util.Arrays;
import java.util.List;

/**
 * @author ANDRE
 * @since 16/05/16
 */
public class GenerateSlide {

    public static Slide createEnglish(){

        Slide slide = new Slide();
        slide.setText("This is the the first first slide");
        List<String> stringList = Arrays.asList("This", "is", "the","the", "the", "first", "first", "slide");
        slide.setKeywordsList(stringList);

        return  slide;
    }

    public static Slide createEnglish_Input(){

        Slide slide = createEnglish();

        DisplayData.input(slide);

        return  slide;
    }

    public static Slide createJapanese(){

        Slide slide = new Slide();
        slide.setText("デジタル画像をコンピュータで");
        List<String> stringList = Arrays.asList("デジ", "タル", "画像", "像をコ", "タで");
        slide.setKeywordsList(stringList);

        return  slide;
    }

    public static Slide createJapanese_Input(){

        Slide slide = createJapanese();

        DisplayData.input(slide);

        return  slide;
    }

    public static List<Slide> createEnglishSlideList(){

        Slide slide1 = new Slide();
        slide1.setText("This is the first slide");
        List<String> stringList1 = Arrays.asList("This", "is", "the", "first", "slide");
        slide1.setKeywordsList(stringList1);

        Slide slide2 = new Slide();
        slide2.setText("This is the second slide");
        List<String> stringList2 = Arrays.asList("This", "is", "the", "second", "slide");
        slide2.setKeywordsList(stringList2);

        List<Slide> slideList = Arrays.asList(slide1,slide2);

        return  slideList;
    }

    public static List<Slide> createEnglishSlideList_Input(){

        List<Slide> slideList = createEnglishSlideList();

        DisplayData.input(slideList);

        return  slideList;
    }

    public static List<Slide> createJapaneseSlideList(){

        Slide slide1 = new Slide();
        slide1.setText("デジタル画像をコンピュータで");
        List<String> stringList1 = Arrays.asList("デジ", "タル", "画像", "像をコ", "タで");
        slide1.setKeywordsList(stringList1);

        Slide slide2 = new Slide();
        slide2.setText("デジタル画像をコンピュータで");
        List<String> stringList2 = Arrays.asList("デジ", "タル", "画像", "像をコ", "タで");

        slide2.setKeywordsList(stringList2);

        List<Slide> slideList = Arrays.asList(slide1,slide2);

        return  slideList;
    }

    public static List<Slide> createJapaneseSlideList_Input(){

        List<Slide> slideList = createJapaneseSlideList();

        DisplayData.input(slideList);

        return  slideList;
    }



    public static List<Slide> createEnglishSlideListEmptyKeywordList(){

        Slide slide1 = new Slide();
        slide1.setText("This is the first slide");

        Slide slide2 = new Slide();
        slide2.setText("This is the second slide");

        List<Slide> slideList = Arrays.asList(slide1,slide2);

        return  slideList;
    }

    public static List<Slide> createEnglishSlideListEmptyKeywordList_Input(){

        List<Slide> slideList = createEnglishSlideListEmptyKeywordList();

        DisplayData.input(slideList);

        return  slideList;
    }


    public static List<Slide> createJapaneseSlideListEmptyKeywordList(){

        Slide slide1 = new Slide();
        slide1.setText("デジタル画像をコンピュータで");

        Slide slide2 = new Slide();
        slide2.setText("デジタル画像をコンピュータで");

        List<Slide> slideList = Arrays.asList(slide1,slide2);

        return  slideList;
    }

    public static List<Slide> createJapaneseSlideListEmptyKeywordList_Input(){

        List<Slide> slideList = createJapaneseSlideListEmptyKeywordList();

        DisplayData.input(slideList);

        return  slideList;
    }

}
