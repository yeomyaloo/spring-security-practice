package com.spring.security.practice.springsecuritypractice.auth.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JwtFailureHandler implements AuthenticationFailureHandler {


    @Value("${server.url}")
    private String url;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("=============== Authentication Failure Handler Start ===================");

        response.sendRedirect(url+"/members/login");

        log.info("=============== Authentication Failure Handler end ===================");

    }
}
