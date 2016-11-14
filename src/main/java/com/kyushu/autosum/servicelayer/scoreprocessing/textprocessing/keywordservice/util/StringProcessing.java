package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

/**
 * @author ANDRE
 * @since 16/05/16
 */
public class StringProcessing {

    /**
     * Transform a String into an array of words
     *
     * @param stringToParse to transforme into an array of words
     * @return the array of words
     */
    public static List<String> parseString(String stringToParse) {

        String[] stringWordsArray = stringToParse.split(" ");

        Predicate<String> notWhitespace = string -> !StringUtils.isWhitespace(string);

        List<String> wordsList = Arrays.stream(stringWordsArray).filter(notWhitespace).collect(Collectors.toList());

        return wordsList;
    }

    /**
     * Clean a string, remove every unwanted characters
     *
     * @param stringToClean to clean
     * @return the string clean
     */
    public static String cleanString(String stringToClean) {

        String stringCleaned;

        // *****************
        // REGULAR CLEANING
        // *****************

        // Replace all number
        stringCleaned = stringToClean.replaceAll("[0-9]+", " ");

        // Replace all special character
        stringCleaned = stringCleaned.replaceAll("[,，.;*$`^/°=§_&:!'?(){}<>%]+", " ");

        // Remove all escape characters
        stringCleaned = stringCleaned.replaceAll("[[\t][\r][\n][\t][\b][\f][\'][\"]]", " ");

        // Remove tab
        stringCleaned = stringCleaned.replaceAll("[\u000b]+", " ");

        // Remove + - characters
        stringCleaned = stringCleaned.replaceAll("[+-]+", " ");

        // *****************
        // JAPANESE CLEANING
        // *****************

        // Remove japanese "
        stringCleaned = stringCleaned.replaceAll("[「」]+", " ");

        // Remove japanese -
        stringCleaned = stringCleaned.replaceAll("[・  ]+", " ");

        // Remove japanese
        stringCleaned = stringCleaned.replaceAll("[[\uF06C]]+", " ");


        // replace all whitespace higher than 1 space into 1 space
        stringCleaned = stringCleaned.replaceAll(" +", " ");

        return stringCleaned;
    }


}
