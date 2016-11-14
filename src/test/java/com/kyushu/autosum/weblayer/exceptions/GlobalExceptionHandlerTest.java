package com.kyushu.autosum.weblayer.exceptions;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by ANDRE on 22/04/16.
 */

@PrepareForTest({ApiError.class,HttpHeaders.class, MediaType.class})
public class GlobalExceptionHandlerTest {

    @Mock
    private ApiError apiError;

    @Mock
    private HttpHeaders httpHeaders;

    @InjectMocks //setups up controller, and injects mock objects into it.
    private GlobalExceptionHandler globalExceptionHandler;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //initilizes controller and mocks
        mockMvc = MockMvcBuilders.standaloneSetup(globalExceptionHandler).build();
    }

    @Test
    public void testHandleNoHandlerFoundException() throws Exception {

        ApiError expectApiError = new ApiError(HttpStatus.NOT_FOUND,"hello", "helle");// = Mockito.mock(ApiError.class);

        PowerMockito.whenNew(ApiError.class)
                .withAnyArguments()
                .thenReturn(expectApiError);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        PowerMockito.whenNew(HttpHeaders.class)
                .withAnyArguments()
                .thenReturn(httpHeaders);

//        Mockito.when(httpHeaders.setContentType(MediaType.APPLICATION_JSON)).thenReturn();

        mockMvc.perform(get("/mat"))
                    .andExpect(status().isNotFound())
                    .andDo(print());
    }


}
