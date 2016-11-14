package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.servicelayer.util.objecthandler.inout.InStream;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which allow extracting text from pptx file
 *
 * @author ANDRE
 */
@Service
public class ParserPPTX implements Parser {

    private static final Logger LOG = LoggerFactory.getLogger(ParserPPTX.class);

    /**
     * Fetch all text from the file
     *
     * @param file to extract text
     * @return the text of the whole file
     */
    @Override
    public String parseFullText(File file) {

        List<String> stringList = new ArrayList<>();

        FileInputStream fileInputStream = InStream.createFileInputStream(file);

        XMLSlideShow xmlSlideShow = createXMLSlideShow(fileInputStream);

        XSLFPowerPointExtractor extractor = new XSLFPowerPointExtractor(xmlSlideShow);

        String fullText = extractor.getText(true, false);

        return fullText;
    }

    /**
     * Fetch the slide text
     *
     * @param file to extract slide text
     * @return a list of text corresponding to each slide in the proper order
     * @throws IOException if file is empty
     */
    @Override
    public List<String> parseSlideText(File file) {

        List<String> stringList = new ArrayList<>();
        FileInputStream fileInputStream = InStream.createFileInputStream(file);

        // Retrieve pptx file
        XMLSlideShow xmlSlideShow = createXMLSlideShow(fileInputStream);

        // Get all slides
        List<XSLFSlide> slideList = xmlSlideShow.getSlides();

        for (int i = 0; i < slideList.size(); i++) {
            // Get slide
            XSLFSheet sheet = slideList.get(i);
            List<XSLFShape> shapeList = sheet.getShapes();

            StringBuilder slideText = new StringBuilder();

            for (XSLFShape shape : shapeList) {

                XSLFTextShape textShape = (XSLFTextShape) shape;

                for (XSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {

                    slideText.append(textParagraph.getText() + " ");
                }
            }
            stringList.add(slideText.toString());
        }
        return stringList;
    }


    XMLSlideShow createXMLSlideShow(FileInputStream fileInputStream) {
        XMLSlideShow xmlSlideShow = null;
        try {
            xmlSlideShow = new XMLSlideShow(fileInputStream);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return xmlSlideShow;
    }

}
