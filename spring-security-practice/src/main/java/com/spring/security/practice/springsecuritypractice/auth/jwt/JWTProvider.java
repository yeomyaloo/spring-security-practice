package com.spring.security.practice.springsecuritypractice.auth.jwt;

import java.util.List;

public class JWTProvider {


    private static final long ACCESS_TOKEN_EXPIRED_TIME = 1000L* 60L * 60L * 2L; //2시간
    private static final long REFRESH_TOKEN_EXPIRED_TIME = 1000L * 60L * 60L * 24L * 7L;

}
