package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util.JapaneseWordSeparator;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util.StringProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ANDRE
 * @since 12/05/16
 */
@Service
public class JapaneseKeywordParser extends KeywordParser {

    private JapaneseWordSeparator japaneseWordSeparator;

    @Autowired
    public void setJapaneseWordSeparator(JapaneseWordSeparator japaneseWordSeparator) {
        this.japaneseWordSeparator = japaneseWordSeparator;
    }


    /**
     * Parse each slide text and create a keyword list
     *
     * @param slideList list of slides
     * @return a list of string corresponding of each keywords
     */
    @Override
    List<Slide> parseSlides(List<Slide> slideList) {

        for (Slide slide : slideList) {

            String text = slide.getText();
            text = StringProcessing.cleanString(text);
            slide.setText(text);

            // add keyword list to slideParseReturn
            List<String> keywordList = separateJapaneseWord(text);

            slide.setKeywordsList(keywordList);
        }
        return slideList;
    }

    /**
     * Separate all japanese words in the text passed as argument
     *
     * @param text to threat
     * @return a list of words
     */
    public List<String> separateJapaneseWord(String text) {

        if (!text.isEmpty()) {
            // Separate words using whitespace
            List<String> keywordList = StringProcessing.parseString(text);

            // Separate japanese words using kuromoji
            List<String> keywordListParsed = keywordList.stream()
                    .map(japaneseWordSeparator::parseJapaneseString)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            return keywordListParsed;
        }

        return Collections.EMPTY_LIST;
    }


}
