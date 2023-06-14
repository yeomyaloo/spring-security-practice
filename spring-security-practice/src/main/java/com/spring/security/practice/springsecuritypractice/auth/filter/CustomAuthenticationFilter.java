package com.spring.security.practice.springsecuritypractice.auth.filter;

import com.spring.security.practice.springsecuritypractice.auth.jwt.JwtProvider;
import com.spring.security.practice.springsecuritypractice.member.exception.InvalidLoginRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/auth/login", "POST");
    private boolean postOnly = true;

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    private static final String AUTHENTICATION = "Authentication ";
    private static final String PREFIX_BEARER = "Bearer ";
    private static final String EXPIRE = "Expire ";

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        log.info("=============== Authentication Filter Start ===================");

        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        log.info("=====================login Id========================= {}", loginId);
        log.info("=====================password========================= {}", password);


        if (!this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        else if (Objects.isNull(loginId) || Objects.isNull(password) || loginId.isBlank() || password.isBlank()){
            throw new InvalidLoginRequestException();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, password);

        return authenticationManager.authenticate(authenticationToken);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info("=============================== successful authentication =================================");
        List<String> roles = authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String loginId = authResult.getName();

        final String USER_UUID = UUID.randomUUID().toString();
        String accessToken = jwtProvider.createAccessToken(loginId, roles);
        String refreshToken = jwtProvider.createRefreshToken(loginId, roles);
        Date date = jwtProvider.extractExpiredTime(accessToken);

        response.addHeader(AUTHENTICATION, PREFIX_BEARER+accessToken);
        response.addHeader(EXPIRE, date.toString());

        SecurityContextHolder.getContext().setAuthentication(authResult);
        log.info("=============================== successful authentication login id ================================= {}", authResult.getName());

    }
}
