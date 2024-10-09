package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.domain.ResponseResult;
import com.example.springsecuritydemo.domain.User;
import com.example.springsecuritydemo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired(required = false)
    private LoginService loginService;

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }
}
