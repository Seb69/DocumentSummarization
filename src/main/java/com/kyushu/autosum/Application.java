package com.kyushu.autosum;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import java.security.Principal;

/**
 * Created by ANDRE on 13/04/16.
 */
@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
@ComponentScan
@Configuration
public class Application extends SpringBootServletInitializer {

//    public static String UploadDir = "AutoSumAPI_V3/src/main/java/com/kyushu/autosum/upload-dir";
    public static String UploadDir = "src/main/java/com/kyushu/autosum/upload-dir";

//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new JwtFilter());
//        registrationBean.addUrlPatterns("/*");
//
//        return registrationBean;
//    }

    public static void main(String[] args) {

        // Authorize display data in awt windows
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        ApplicationContext ctx = builder.run(args);

        // Normal load context
//        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        // Configuration DispatcherServlet : disable default error (Whitelable Error)
        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory =
                new TomcatEmbeddedServletContainerFactory();
        return factory;
    }

}
