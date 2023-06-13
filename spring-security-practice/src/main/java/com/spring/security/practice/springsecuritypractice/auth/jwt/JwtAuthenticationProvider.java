package com.spring.security.practice.springsecuritypractice.auth.jwt;

import com.spring.security.practice.springsecuritypractice.member.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {


        String loginId = auth.getName();
        String password = (String) auth.getCredentials();

        log.info("=====================Encode password========================= {}", passwordEncoder.encode(password));

        UserDetails user = userDetailsService.loadUserByUsername(loginId);


        if(Objects.isNull(user)) {
            throw new BadCredentialsException("user id not found!");
        }
        else if (!this.passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("password is not matches");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginId,
                null,
                user.getAuthorities()
        );
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
