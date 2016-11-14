package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice.util;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which parse a japanses sentence into separate words
 *
 * @author ANDRE
 * @since 14/05/16
 */
@Service
public class JapaneseWordSeparator {

    /**
     * Separate a japaneses string into a list of words
     *
     * @param string to parse
     * @return list of string
     */
    public List<String> parseJapaneseString(String string) {

        List<String> stringList = new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer.Builder().build();

        for (Token token : tokenizer.tokenize(string)) {

            String tokenString = token.getSurface();
            stringList.add(tokenString);
        }
        return stringList;
    }
}
