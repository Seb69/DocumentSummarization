package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.calculation;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Calculation of Inverse Document Frequency (idf)
 * <p>
 * idf (Word, CompleteDocument) = log ( NumberOfPages / (Number of occurence of "Word" in "CompleteDocument"))
 * <p>
 * idf = A
 * NumberOfPages = B
 * Number of occurrence = C
 * <p>
 * A = log ( B / C )
 *
 * @author ANDRE
 * @since 16/05/16
 */
public class InverseDocumentFrequency {

    /**
     * Calculate the idf (A)
     *
     * @param material the document of calculation
     * @return a map of words and their idf score (A)
     */
    public static Map<String, Double> calculationInverseTermFrequency(Material material) {

        long N = numberOfPages(material.getSlideList());

        Map<String, Long> occurrenceWord = calculationOccurrenceWord(material);

        Map<String, Double> inverseTermFrequencyMap = material.getKeywordsList().stream()
                .distinct()
                .collect(Collectors.toMap(
                        e -> e,
                        e -> Math.log10(N / occurrenceWord.get(e))
                ));

        return inverseTermFrequencyMap;

    }

    /**
     * Calculate the number of pages in the document (B)
     *
     * @param slideList list of slides
     * @return the number of slides (B)
     */
    static long numberOfPages(List<Slide> slideList) {

        return slideList.stream().count();
    }

    /**
     * This method calculate the number of slide where each word of material appear (C)
     *
     * @return a map of each keyword with its number of slide where it appears (C)
     */
    static Map<String, Long> calculationOccurrenceWord(Material material) {

        Map<String, Long> occurrence = material.getKeywordsList().stream()
                .distinct()
                .collect(Collectors.toMap(
                        string -> string,
                        string -> material.getSlideList().stream()
                                .filter(slide -> isWordInsideSlide(slide, string)).count()
                ));

        return occurrence;
    }

    /**
     * Determine whether a word is in a document
     *
     * @param slide where to search
     * @param word  to find
     * @return true if found, else not found
     */
    static boolean isWordInsideSlide(Slide slide, String word) {

        boolean isPresent = slide.getKeywordsList().stream()
                .distinct()
                .anyMatch(keyword -> keyword.equals(word));

        return isPresent;

    }

}
