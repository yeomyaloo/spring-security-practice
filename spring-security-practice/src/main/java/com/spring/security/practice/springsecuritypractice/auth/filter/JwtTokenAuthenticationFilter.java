package com.spring.security.practice.springsecuritypractice.auth.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 세션을 대신해서 jwt를 통한 인증 객체를 security context에 저장할 떄 사용합니다.
 * 이때 onceperrequest를 사용해서 여러번의 요청에 동작하는 필터가 아닌 한 번만 동작할 수 있게 했습니다.
 * */

@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String accessToken = request.getHeader("Authorization");
        if(Objects.isNull(accessToken)){
            log.info("OncePerRequestFilter before = ");
            filterChain.doFilter(request,response);
            return;
        }

        log.info("OncePerRequestFilter after =========== {}", accessToken);
        filterChain.doFilter(request,response);

    }
}
