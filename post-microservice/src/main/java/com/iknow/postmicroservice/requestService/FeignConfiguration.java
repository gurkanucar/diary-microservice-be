package com.iknow.postmicroservice.requestService;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor
            (@Value("${security.username}") String username,
             @Value("${security.password}") String password) {
        return new BasicAuthRequestInterceptor(username, password);
    }


}