package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.keywordservice;

import com.kyushu.autosum.repositorylayer.domain.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class which parse all slide text into a list of keywords
 *
 * @author ANDRE
 * @since 15/05/16
 */
@Service
public class ParseMaterialKeywordServiceImpl implements ParseMaterialKeywordService {

    private ParserKeywordFactory parserKeywordFactory;

    @Autowired
    public void setParserKeywordFactory(ParserKeywordFactory parserKeywordFactory) {
        this.parserKeywordFactory = parserKeywordFactory;
    }

    /**
     * Get the right keywordParser and parse the material
     *
     * @param material to parse into keyword
     * @return a material with each slide parse into keyword list
     */
    @Override
    public Material parseMaterialKeyword(Material material) {

        KeywordParser keywordParser = parserKeywordFactory.getKeywordParser(material.getText());

        material = keywordParser.parseMaterial(material);

        material = keywordParser.concatSlideParsing(material);

        return material;
    }
}
