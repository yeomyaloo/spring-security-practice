package com.spring.security.practice.springsecuritypractice.member.exception;

public class InvalidLoginRequestException extends RuntimeException {
    public InvalidLoginRequestException() {
        super("this is invalid login request exception");
    }
}
