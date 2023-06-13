package com.spring.security.practice.springsecuritypractice.member.controller.rest;


import com.spring.security.practice.springsecuritypractice.member.domain.dto.request.MemberLoginRequest;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/members")
public class MemberRestController {

    @GetMapping("/test")
    public ResponseEntity addHeaderTest(){
        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.add("expired", "20000");
        HttpEntity entity = new HttpEntity(httpHeaders);

        return ResponseEntity.ok(entity);

    }



}
