package com.kyushu.autosum.configurationlayer.security;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import org.apache.cxf.security.claims.authorization.Claims;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;
import io.jsonwebtoken.Jwts;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.FileCopyUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * Tester : ResourceServerConfiguration
 *
 * @author ANDRE
 * @since 27/07/16
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceServerConfiguration_Test {

    @InjectMocks
    ResourceServerConfiguration resourceServerConfiguration = new ResourceServerConfiguration();

    @Test
    public void testJWT() throws Exception {

        // BUILD
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE0Njk1NjY4ODYsImF1dGhvcml0aWVzIjpbIlVTRVJfUk9MRSJdLCJqdGkiOiIxZmFkYWQ3Mi0yM2EwLTQwNDMtOTlkMy1mMDdkMjA4YWZmNDEiLCJjbGllbnRfaWQiOiJ3ZWJfYXBwIn0.fohJsTpuUt_5KCJCEYJhTKCRb_vfxnRyWKjzd6YmqUfdcyWBKkTPNPcPDruNmOPzaec10O8KM8Pyrhdgfq4yBOu0EqzF4sPOhjGX_hFZPVKjqOCehvkHAnbyqhNanOz4GHdSuD8G-eP5_iThuZ4flygYrVSD8mabti0n5JX8Fb7tsKgtzXbLUvL4btETOrRRHyUy8jvyOiuJmFjEW1EFXjPhMuVdr2hjmdLMpzUL64QeEt7o9x0m4L7LCPeCGd7tIvXru8g2qDbt4qVJC1m4Ba21ZKJhlTVlejWPY22JAnPLmzJvjkFByCJ-bkokIgcAoevPo6dqmNFrXUzNOUP1IA";

        // OPERATE

        Resource resource = new ClassPathResource("public.cert");
        String publicKey = null;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // CHECK
//        DisplayData.output(claims.realm());


    }
}
