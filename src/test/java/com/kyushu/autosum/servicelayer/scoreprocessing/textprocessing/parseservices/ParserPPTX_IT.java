package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

import static com.mchange.v2.resourcepool.ResourcePoolFactory.createInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author ANDRE
 * @date 03/05/16
 */
public class ParserPPTX_IT extends AbstractIntegrationTest {

    private ParserPPTX parserPPTX;

    @Autowired
    public void setParserPPTX(ParserPPTX parserPPTX) {
        this.parserPPTX = parserPPTX;
    }

    @Test
    public void parserFullText_File__String() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX();

        // OPERATE
        String text = parserPPTX.parseFullText(file);

        // CHECK
        DisplayData.output(text);
        assertEquals(false, text.isEmpty());
    }

    @Test
    public void parseSlideText_File__StringList() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX();

        // OPERATE
        List<String> stringList = parserPPTX.parseSlideText(file);

        // CHECK
        DisplayData.output(stringList);
        assertEquals(false, stringList.isEmpty());

    }

    @Test
    public void parseFullText_FileJP__String() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX_JP();

        // OPERATE
        String text = parserPPTX.parseFullText(file);

        // CHECK
        DisplayData.output(text);
        assertEquals(false, text.isEmpty());

    }

    @Test
    public void parseSlideText_FileJP__ListString() throws Exception {

        // BUILD
        File file = GenerateFile.createPPTX_JP();

        // OPERATE
        List<String> stringList = parserPPTX.parseSlideText(file);

        // CHECK
        DisplayData.output(stringList);
        assertEquals(false, stringList.isEmpty());

    }
}
