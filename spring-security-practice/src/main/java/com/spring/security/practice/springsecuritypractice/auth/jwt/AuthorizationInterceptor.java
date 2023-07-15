package com.spring.security.practice.springsecuritypractice.auth.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("uri =============",uri);

        log.info("pre =============",request.getHeader("Authorization"));


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        log.info("uri =============",uri);

        log.info("post =============", request.getHeader("Authorization"));

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}
