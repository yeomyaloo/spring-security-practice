package com.spring.security.practice.springsecuritypractice.auth.filter;

import com.spring.security.practice.springsecuritypractice.auth.jwt.JwtProvider;
import com.spring.security.practice.springsecuritypractice.member.domain.dto.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.spring.security.practice.springsecuritypractice.auth.AuthUtils.*;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String TOKEN_EXPIRE = "Token-expire";

    @Resource(name = "jwtProvider")
    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");


        //UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(loginId, password);
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(loginId, password);

        return getAuthenticationManager().authenticate(unauthenticated);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {


        //jwt 발급
        String accessToken = getAccessToken(auth);
        String refreshToken = getRefreshToken(auth);

        String expiredTime = jwtProvider.extractExpiredTime(accessToken).toString();

        //uuid 생성 ( 쿠키에 사용, key값으로 사용 redis 저장 key : 토큰정보, 회원 역할 )
        final String USER_UUID = UUID.randomUUID().toString();

        // redis에 위의 정보 저장
        redisTemplate.opsForHash().put(USER_UUID, ACCESS_TOKEN.getValue(), accessToken);
        redisTemplate.opsForHash().put(USER_UUID, REFRESH_TOKEN.getValue(), refreshToken);

        AuthMember authMember = new AuthMember(
                auth,
                accessToken,
                expiredTime
        );

        redisTemplate.opsForHash().put(USER_UUID, JWT.getValue(),authMember);

        response.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + accessToken);
        response.addHeader(UUID_HEADER.getValue(), USER_UUID);
        response.addHeader(TOKEN_EXPIRE, expiredTime);


    }



    private String getAccessToken(Authentication auth) {
        return jwtProvider.createAccessToken(
                auth.getName(),
                auth.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
    }
    private String getRefreshToken(Authentication auth) {
        return jwtProvider.createRefreshToken(
                auth.getName(),
                auth.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
    }


}
