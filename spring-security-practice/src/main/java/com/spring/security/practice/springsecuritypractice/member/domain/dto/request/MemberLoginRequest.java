package com.spring.security.practice.springsecuritypractice.member.domain.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberLoginRequest {


    private String loginId;

    private String password;
}
