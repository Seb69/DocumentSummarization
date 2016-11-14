package com.kyushu.autosum.servicelayer.util.objecthandler.file;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

/**
 * Tester : FileHandler
 *
 * @author ANDRE
 * @since 28/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class FileHandler_Test {

    @InjectMocks
    FileHandler fileHandler = new FileHandler();

    @Test
    public void createNewFile_File__File() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        file = FileHandler.createNewFile(file);

        // CHECK
        assertEquals(true, file.isFile());
        assertEquals(true, file.exists());
    }


}