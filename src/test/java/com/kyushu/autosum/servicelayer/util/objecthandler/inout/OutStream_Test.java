package com.kyushu.autosum.servicelayer.util.objecthandler.inout;

import static org.junit.Assert.*;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import com.kyushu.autosum.servicelayer.util.objecthandler.inout.exception.NoSuchFileException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;

/**
 * Tester : OutStream
 *
 * @author ANDRE
 * @since 28/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class OutStream_Test {

    @InjectMocks
    OutStream outStream = new OutStream();

    @Test
    public void createFileOutputStream_File__FileOutputStream() throws Exception {

        // BUILD
        File file = GenerateFile.createError();

        // OPERATE
        FileOutputStream fileOutputStream = outStream.createFileOutputStream(file);

        // CHECK
        fileOutputStream.write(42);

    }
    @Test (expected = NoSuchFileException.class)
    public void createFileOutputStream_File__E_IOException() throws Exception {

        // BUILD
        File file1 = new File("");

        // OPERATE
        FileOutputStream fileOutputStream = outStream.createFileOutputStream(file1);

    }


    @Test
    public void closeOutputStream_OutputStream() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();
        OutputStream outputStream = new FileOutputStream(file);

        // OPERATE
        OutStream.closeOutputStream(outputStream);

        // CHECK
        try
        {
            outputStream.write(42);
        }
        catch (IOException e)
        {
            assertEquals(e.getLocalizedMessage(), "Stream Closed");
        }

    }

    @Test
    public void closeOutputStream_FileOutputStream() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        // OPERATE
        OutStream.closeOutputStream(fileOutputStream);

        // CHECK
        try
        {
            fileOutputStream.write(42);
        }
        catch (IOException e)
        {
            assertEquals(e.getLocalizedMessage(), "Stream Closed");
        }

    }

    @Test
    public void closeOutputStream_BufferOutputStream() throws Exception {

        // BUILD
        File file = GenerateFile.createPDF();
        OutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        // OPERATE
        OutStream.closeOutputStream(bufferedOutputStream);

        // CHECK
        try
        {
            bufferedOutputStream.write(42);
        }
        catch (IOException e)
        {
            assertEquals(e.getLocalizedMessage(), "Stream Closed");
        }

    }

    @Test (expected = NoSuchFileException.class)
    public void closeOutputStream_OutputStream__E_NullPointerException() throws Exception {

        // BUILD
        OutputStream outputStream = null;

        // OPERATE
        OutStream.closeOutputStream(outputStream);

    }

    @Test (expected = NoSuchFileException.class)
    public void closeOutputStream_FileOutputStream__E_NullPointerException() throws Exception {

        // BUILD
        FileOutputStream fileOutputStream = null;

        // OPERATE
        OutStream.closeOutputStream(fileOutputStream);

    }

    @Test (expected = NoSuchFileException.class)
    public void closeOutputStream_BufferOutputStream__E_NullPointerException() throws Exception {

        // BUILD
        BufferedOutputStream bufferedOutputStream = null;

        // OPERATE
        OutStream.closeOutputStream(bufferedOutputStream);

    }


}
    