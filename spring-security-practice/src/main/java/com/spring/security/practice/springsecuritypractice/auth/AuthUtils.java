package com.spring.security.practice.springsecuritypractice.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AuthUtils {

    ACCESS_TOKEN("ACCESS_TOKEN"),
    REFRESH_TOKEN("REFRESH_TOKEN"),
    USER_ID("USER_ID"),

    UUID_HEADER("UUID_HEADER"),
    PRINCIPALS("PRINCIPAL"),
    JWT("JWT")

    ;
    private String value;


}
