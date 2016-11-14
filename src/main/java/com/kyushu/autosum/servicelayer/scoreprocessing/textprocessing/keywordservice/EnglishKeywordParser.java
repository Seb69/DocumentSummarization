package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util.StringProcessing;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ANDRE
 * @since 14/05/16
 */
@Service
public class EnglishKeywordParser extends KeywordParser {

    /**
     * Parse each slide text and create a keyword list
     *
     * @param slideList </Slide> to parse
     * @return a list of string corresponding of each keywords
     */
    @Override
    List<Slide> parseSlides(List<Slide> slideList) {

        if (!slideList.isEmpty()) {

            for (Slide slide : slideList) {

                String text = slide.getText();
                text = StringProcessing.cleanString(text);
                slide.setText(text);

                // add keyword list to slideParseReturn
                List<String> keywordList = StringProcessing.parseString(text);
                slide.setKeywordsList(keywordList);
            }
            return slideList;
        }
        return slideList;
    }


}
