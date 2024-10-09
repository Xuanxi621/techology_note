package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.domain.ResponseResult;
import com.example.springsecuritydemo.domain.User;

public interface LoginService {
    ResponseResult login(User user);
}
