package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by ANDRE on 01/05/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParserPPT_Test {

    @InjectMocks
    Parser parserPPT = new ParserPPT();

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
