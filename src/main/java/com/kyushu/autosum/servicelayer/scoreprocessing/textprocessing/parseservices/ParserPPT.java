package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.servicelayer.util.objecthandler.inout.InStream;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which allow extracting text from ppt file
 *
 * @author ANDRE
 * @date 08/05/16
 */
@Service
public class ParserPPT implements Parser {

    private static final Logger LOG = LoggerFactory.getLogger(ParserPPT.class);

    /**
     * Fetch all text from the file
     *
     * @param file to extract text
     * @return the text of the whole file
     */
    @Override
    public String parseFullText(File file) {

        NPOIFSFileSystem npoifsFileSystem = createNPOIFSFileSystem(file);
        PowerPointExtractor extractor = createPowerPointExtractor(npoifsFileSystem);

        return extractor.getText(true, false);
    }

    /**
     * Fetch the slide text
     *
     * @param file to extract slide text
     * @return a list of text corresponding to each slide in the proper order
     */
    @Override
    public List<String> parseSlideText(File file) {

        List<String> stringList = new ArrayList<>();
        FileInputStream inputStream = InStream.createFileInputStream(file);
        HSLFSlideShow slideShow = createHSLFSlideShow(inputStream);

        List<HSLFSlide> slideList = slideShow.getSlides();

        for (HSLFSlide aSlideList : slideList) {

            List<List<HSLFTextParagraph>> textParagraphListList = aSlideList.getTextParagraphs();
            String slideText = "";

            for (List<HSLFTextParagraph> textParagraphList : textParagraphListList) {

                List<HSLFTextRun> textRunList = textParagraphList.get(0).getTextRuns();
                slideText = slideText.concat(textRunList.get(0).getRawText() + " ");
            }
            stringList.add(slideText);
        }
        return stringList;
    }

    NPOIFSFileSystem createNPOIFSFileSystem(File file) {
        NPOIFSFileSystem npoifsFileSystem = null;
        try {
            npoifsFileSystem = new NPOIFSFileSystem(file);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return npoifsFileSystem;
    }

    PowerPointExtractor createPowerPointExtractor(NPOIFSFileSystem npoifsFileSystem) {
        PowerPointExtractor extractor = null;
        try {
            extractor = new PowerPointExtractor(npoifsFileSystem);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return extractor;
    }

    HSLFSlideShow createHSLFSlideShow(InputStream inputStream) {
        HSLFSlideShow hslfSlideShow = null;
        try {
            hslfSlideShow = new HSLFSlideShow(inputStream);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return hslfSlideShow;
    }

}
