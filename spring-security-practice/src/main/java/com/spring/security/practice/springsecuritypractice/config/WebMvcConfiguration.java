package com.spring.security.practice.springsecuritypractice.config;


import com.querydsl.core.annotations.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//
//
//        registry.addInterceptor(new AuthorizationInterceptor());
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
}
