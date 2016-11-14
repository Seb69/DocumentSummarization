package com.kyushu.autosum.repositorylayer.generators;

import com.kyushu.autosum.repositorylayer.display.DisplayData;

import java.util.Arrays;
import java.util.List;

/**
 * @author ANDRE
 * @since 16/05/16
 */
public class GenerateString {


    public static List<String> createEnglishStringList(){


        List<String> stringList = Arrays.asList("hello", "hello", "hello1", "hello2");

        DisplayData.input(stringList);

        return stringList;

    }

    public static List<String> createJapaneseStringList(){

        List<String> stringList = Arrays.asList("デジ", "処理", "をコン", "すること", "すること");

        DisplayData.input(stringList);

        return stringList;

    }

    public static String createJapaneseString(){

        String string = "デジタル画像をコンピュータで";

        DisplayData.input(string);

        return string;

    }

}
