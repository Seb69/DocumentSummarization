package com.kyushu.autosum.servicelayer.util.objecthandler.inout;

import static org.junit.Assert.*;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;

/**
 * Tester : InStream
 *
 * @author ANDRE
 * @since 28/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class InStream_Test {

    @InjectMocks
    InStream inStream = new InStream();

    @Test
    public void createFileInputStream_File__FileInputStream() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        FileInputStream fileInputStream = inStream.createFileInputStream(file);

        // CHECK
        try {
            fileInputStream.read();
        } catch (IOException e) {
            assertEquals(e.getLocalizedMessage(), "Stream Closed");
        }

    }

    @Test
    public void createInputStream_file__FileInputStream() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();

        // OPERATE
        FileInputStream fileInputStream = inStream.createFileInputStream(file);

        // CHECK
        assertNotEquals(0,fileInputStream.read());

    }

    @Test
    public void closeInputStream_InputStream() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();
        InputStream inputStream = new FileInputStream(file);

        // OPERATE
        InStream.closeInputStream(inputStream);

        // CHECK
        try
        {
            inputStream.read();
        }
        catch (IOException e)
        {
            assertEquals(e.getLocalizedMessage(), "Stream Closed");
        }


    }


}
    