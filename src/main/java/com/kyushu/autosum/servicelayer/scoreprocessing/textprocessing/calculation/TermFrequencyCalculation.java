package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

/**
 * This class allow the calculation of term frequency (tf)
 * <p>
 * tf(Word, SubDocument) = number of occurrence of "Word" in "SubDocument"
 *
 * @author ANDRE
 * @since 16/05/16
 */
public class TermFrequencyCalculation {

    /**
     * Calculate the frequency (tf) or each words
     *
     * @param stringList list of words to calculate tf
     * @return a map with each words and its number of occurrence in document
     */
    public static Map<String, Long> calculateTermFrequency(List<String> stringList) {

        if (stringList.isEmpty()) return Collections.EMPTY_MAP;

        Map<String, Long> mapList = stringList.stream()
                .collect(Collectors.groupingBy(
                        String::toString,
                        counting()
                ));
        return mapList;
    }

}
