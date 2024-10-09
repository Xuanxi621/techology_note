package com.example.springsecuritydemo.service.impl;

import com.example.springsecuritydemo.domain.LoginUser;
import com.example.springsecuritydemo.domain.ResponseResult;
import com.example.springsecuritydemo.domain.User;
import com.example.springsecuritydemo.service.LoginService;
import com.example.springsecuritydemo.utils.JwtUtil;
import com.example.springsecuritydemo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Autowired(required = false)
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过,给出对应提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        // 如果认证通过,使用userid生成一个jwt,jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
        // 把完整的用户信息存入redis userid作为key
        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseResult(200,"登录成功",map);
    }
}
