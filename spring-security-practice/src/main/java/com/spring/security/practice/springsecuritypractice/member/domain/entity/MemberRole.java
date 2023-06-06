package com.spring.security.practice.springsecuritypractice.member.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum MemberRole {

    ROLE_ADMIN(1,"admin"),ROLE_USER(2,"user");

    private int roleId;
    private String roleName;
}
