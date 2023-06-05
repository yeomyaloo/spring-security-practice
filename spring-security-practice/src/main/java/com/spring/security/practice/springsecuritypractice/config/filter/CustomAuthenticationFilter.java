package com.spring.security.practice.springsecuritypractice.config.filter;

import com.spring.security.practice.springsecuritypractice.member.exception.InvalidLoginRequestException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/members/login", "POST");
    private boolean postOnly = true;

    public CustomAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        if (!this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        else if (Objects.isNull(loginId) || Objects.isNull(password)){
            throw new InvalidLoginRequestException();

        }

        Authentication authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(loginId, password)
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
