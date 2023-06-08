package com.spring.security.practice.springsecuritypractice.config;


import com.spring.security.practice.springsecuritypractice.auth.filter.CustomAuthenticationFilter;
import com.spring.security.practice.springsecuritypractice.auth.filter.JwtAuthenticationFilter;
import io.jsonwebtoken.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 스프링 시큐리티와 관련해서 환경 설정을 진행하는 클래스입니다.
 */
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors().disable();
        http.formLogin()
                .loginPage("/members/login");


        // 관리자, 로그인할 때 볼수 있는 마이페이지 제외하고 모든 요청은 권한 없이 접속 가능
        http.authorizeHttpRequests()
                .anyRequest().permitAll()
                .antMatchers("/myPage/**", "/manager/**").authenticated();

        http.addFilter(jwtAuthenticationFilter());
        return http.build();
    }

    private UsernamePasswordAuthenticationFilter jwtAuthenticationFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();

        jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
        //jwtAuthenticationFilter.setAuthenticationFailureHandler();
        return jwtAuthenticationFilter();
    }

    /**
     * 비밀번호 평문 저장을 방지하기 위한 엔코더 빈등록
     * */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }
}
