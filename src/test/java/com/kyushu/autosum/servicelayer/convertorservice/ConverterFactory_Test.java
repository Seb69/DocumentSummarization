package com.kyushu.autosum.servicelayer.convertorservice;

import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tester : ConverterFactory
 *
 * @author ANDRE
 * @since 28/05/16
 */
@RunWith(MockitoJUnitRunner.class)
public class ConverterFactory_Test {

    // ENABLE TO TEST BECAUSE FUNCTION RETURN AUTOWIRED OBJECT
    // INTEGRATION TEST ONLY AVAILABLE

    @InjectMocks
    ConverterFactory converterFactory = new ConverterFactory();


}
