package com.example.springsecuritydemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecuritydemo.domain.User;
import com.example.springsecuritydemo.mapper.UserMapper;
import jakarta.servlet.http.PushBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

    @Autowired(required = false)
    private UserMapper userMapper;


    @Test
    public void testMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setUserName("xuanxi");
        user.setPassword("123456");
        userMapper.insert(user);
    }

    @Test
    public void selectUser(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,"xuanxi");
        lambdaQueryWrapper.eq(User::getPassword,"123456");
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(bCryptPasswordEncoder.matches("123456",
                encode));

    }
}
