package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ANDRE
 * @date 03/05/16
 */
public class ParserPPT_IT extends AbstractIntegrationTest {

    private ParserPPT parserPPT;

    @Autowired
    public void setParserPPT(ParserPPT parserPPT) {
        this.parserPPT = parserPPT;
    }

    @Test
    public void parseFullText_File__String() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // OPERATE
        String text = parserPPT.parseFullText(file);

        // CHECK
        DisplayData.output(text);
        assertEquals(false, text.isEmpty());
    }

    @Test
    public void parseSlideText_File__StringList() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT();

        // OPERATE
        List<String> stringList = parserPPT.parseSlideText(file);

        // CHECK
        DisplayData.output(stringList);
        assertEquals(false, stringList.isEmpty());

    }

}
