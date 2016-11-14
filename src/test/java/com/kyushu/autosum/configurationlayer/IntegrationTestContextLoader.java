package com.kyushu.autosum.configurationlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.SpringApplicationContextLoader;

/**
 * Load the context in order to display frame panel
 * @author ANDRE
 * @since 21/05/16
 */
public class IntegrationTestContextLoader extends SpringApplicationContextLoader {

    @Override
    protected SpringApplication getSpringApplication() {
        return new SpringApplicationBuilder().headless(false).build();
    }

}
