package com.spring.security.practice.springsecuritypractice.common.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class WebController {


    @GetMapping("/")
    public String index(){

        log.info("authentication? === ", SecurityContextHolder.getContext().getAuthentication().toString());
        return "index";


    }
}
