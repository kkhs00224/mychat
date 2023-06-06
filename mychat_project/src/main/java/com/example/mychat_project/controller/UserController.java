package com.example.mychat_project.controller;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello(Authentication authentication){
        if(authentication != null){
            System.out.println("authentication 정보 : " + authentication.getClass());

            // 세션 객체 반환
            WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
            System.out.println("세션 ID : " + web.getSessionId());
            System.out.println("접속 IP : " + web.getRemoteAddress());

            // UserDetails 객체 반환
            UserDetails userVo = (UserDetails) authentication.getPrincipal();
            System.out.println("ID 정보 : " + userVo.getUsername());
            System.out.println("권한 정보 : " + userVo.getAuthorities());
        }
        return "hello";
    }
}
