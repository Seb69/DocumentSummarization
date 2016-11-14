package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which parse pdf file
 *
 * @author ANDRE
 * @date 03/06/16
 */
@Service
public class ParserPDF implements Parser {

    private static final Logger LOG = LoggerFactory.getLogger(ParserPDF.class);

    /**
     * Fetch the text of the whole file
     *
     * @param file to extract text
     * @return a string of text
     */
    @Override
    public String parseFullText(File file) {

        PdfReader reader = pdfReader(file.getPath());

        String textOfPage;

        StringBuilder returnText = new StringBuilder();

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            StringBuilder textOfPageConcat = new StringBuilder();
            textOfPage = getTextFromPage(reader, i);

            for (int t = 0; t < textOfPage.toCharArray().length; t++) {

                char letter = textOfPage.toCharArray()[t];

                String string = removeEscapeCharacter(letter);
                textOfPageConcat.append(string);
            }
            returnText.append(textOfPageConcat.toString());
        }
        return returnText.toString();
    }

    /**
     * Fetch all slide text
     *
     * @param file to extract text
     * @return a list of string stands for each slide
     */
    @Override
    public List<String> parseSlideText(File file) {

        List<String> stringList = new ArrayList<>();

        PdfReader reader = pdfReader(file.getPath());
        String textOfPage;

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            StringBuilder textOfPageConcat = new StringBuilder();

            textOfPage = getTextFromPage(reader, i);

            for (int t = 0; t < textOfPage.toCharArray().length; t++) {

                char letter = textOfPage.toCharArray()[t];

                String string = removeEscapeCharacter(letter);
                textOfPageConcat.append(string);
            }

            stringList.add(textOfPageConcat.toString());

        }
        return stringList;

    }

    /**
     * Remove all escape characters from the string
     *
     * @param character to threat
     * @return a string without escape characters
     */
    String removeEscapeCharacter(char character) {

        String string = String.valueOf(character);

        if (string.equals("\r") ||
                string.equals("\t") ||
                string.equals("\n") ||
                string.equals("\b") ||
                string.equals("\f") ||
                string.equals("\'") ||
                string.equals("\"") ||
                string.equals(("\\"))) {

            string = "";
        }

        return string;
    }


    /**
     * Handle IOException
     *
     * @param filePath to create the reader
     * @return the reader
     */
    PdfReader pdfReader(String filePath) {

        PdfReader reader = null;
        try {
            reader = new PdfReader(filePath);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return reader;
    }

    /**
     * Handle IOException
     *
     * @param reader     the reader of the document
     * @param pageNumber the page number
     * @return the text of the page
     */
    String getTextFromPage(PdfReader reader, int pageNumber) {

        StringBuilder textOfPage = new StringBuilder();
        try {
            textOfPage.append(PdfTextExtractor.getTextFromPage(reader, pageNumber));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return textOfPage.toString();

    }

}
