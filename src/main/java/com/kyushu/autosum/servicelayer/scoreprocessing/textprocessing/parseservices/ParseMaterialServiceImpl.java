package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices.exceptions.FileNotSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class parse file into object, Material is the return object with all text field fill
 *
 * @author ANDRE
 * @date 03/06/16
 */
@Service
public class ParseMaterialServiceImpl implements ParseMaterialService {

    private static final Logger LOG = LoggerFactory.getLogger(ParseMaterialServiceImpl.class);

    private Parser parser;

    private ParserFactory parserFactory;

    @Autowired
    public void setParserFactory(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    /**
     * Fetch the full text of the file
     *
     * @param file to parse ( support : ppt, pptx, pdf
     * @return Material object with only text field complete
     */
    @Override
    public Material parseMaterial(File file) {

        Material material = new Material();
        material.setMaterialFilePATH(file.getPath());

        // Get the Parser
        this.parser = createParser(file);

        // Retrieve full text of file
        String fullTextExtracted = parser.parseFullText(file);
        material.setText(fullTextExtracted);

        // Set all slides text
        List<Slide> slideList = parseSlides(file);

        // Set slideList to material
        material.setSlideList(slideList);

        return material;
    }

    /**
     * Fetch all the slide text
     *
     * @param file to parse
     * @return list of slide with text field filled
     */
    List<Slide> parseSlides(File file) {

        List<Slide> slideList = new ArrayList<>();
        List<String> pagesTextList = parser.parseSlideText(file);

        for (String pageText : pagesTextList) {
            Slide slide = new Slide();
            slide.setText(pageText);
            slideList.add(slide);
        }
        return slideList;
    }

    /**
     * Handle IOException
     *
     * @param file to create the parser
     * @return the parser created
     */
    Parser createParser(File file) {
        Parser parser = null;

        try {
            parser = parserFactory.createParser(file);
        } catch (FileNotSupport fileNotSupport) {
            LOG.error(fileNotSupport.getMessage());
        }
        return parser;
    }

}