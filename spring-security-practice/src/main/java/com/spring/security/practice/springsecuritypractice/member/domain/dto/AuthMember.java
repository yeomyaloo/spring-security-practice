package com.spring.security.practice.springsecuritypractice.member.domain.dto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * jwt 토큰 정보를 redis애 저장하기 위해 직렬화 작업에 사용하는 회원정보 클래스입니다.
 *
 * 1. object -> redis 저장
 * 2. redis (byte array 형식의 데이터 저장)
 * 3. object -> byte[]로 변환 -> redis 저장
 *
 * 반대 상황?
 * 1. byte[] -> object 변환
 * 2. redis 내에 데이터는 byte[] 형식으로 저장되어 있음
 * 3. byte[] -> object 변환 -> 객체 사용
 * */
public class AuthMember implements Serializable {

    private String loginId;

    private List<String> roles;
    private String accessToken;
    private String expiredTime;

    public AuthMember(Authentication auth,
                      String accessToken, String expiredTime) {
        this.loginId = auth.getName();
        this.roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        this.accessToken = accessToken;
        this.expiredTime = expiredTime;
    }
}
