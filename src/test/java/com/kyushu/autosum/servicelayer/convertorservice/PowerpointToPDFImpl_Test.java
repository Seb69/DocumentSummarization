package com.kyushu.autosum.servicelayer.convertorservice;

import com.kyushu.autosum.repositorylayer.generators.GenerateFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Tester : PowerPointConverter
 *
 * @author ANDRE
 * @since 25/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class PowerpointToPDFImpl_Test {

    @InjectMocks
    PowerpointToPDFImpl powerPointConverterImpl = new PowerpointToPDFImpl();

    @Test
    public void convertToPDF() throws Exception {

        // BUILD
        File file = GenerateFile.createPPT_Server();

        // OPERATE
        file = powerPointConverterImpl.convertToPDF(file);

        // CHECK
        assertEquals(true, file.isFile());
        assertEquals(true, file.exists());

    }

    @Test
    public void searchWorkspaceFolder() throws Exception {

        // OPERATE
        powerPointConverterImpl.searchWorkspaceFolder();

    }

    @Test
    public void checkWorkspaceFolder() throws Exception {

        // OPERATE
        powerPointConverterImpl.checkWorkspaceFolder();

    }
}
