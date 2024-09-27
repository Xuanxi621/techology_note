package com.example.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.example.mybatisplusdemo.domian.User;
import com.example.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@SpringBootTest
class MyBatisPlusDemoApplicationTests {


    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void testQueryList(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSelect(){
        System.out.println("------ Select Method Test -----");
        List<User> users = userMapper.selectList(null);
        Assert.isTrue(5 == users.size(),"");
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("PiD");
        user.setAge(19);
        user.setEmail("test9@baomidou.com");
        int userId = userMapper.insert(user); // userId : 雪花算法生成
        System.out.println(userId);
    }


    @Test
    public void testDelete(){
        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(4);
        int result = userMapper.deleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testDeleteMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","PiD");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(2L);
        user.setAge(29);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("age",18);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testQuery(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id","name");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testClassMapper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(new User());
        userQueryWrapper.select(new Predicate<TableFieldInfo>() {
            @Override
            public boolean test(TableFieldInfo tableFieldInfo) {
                return !"email".equals(tableFieldInfo.getColumn());
            }
        });
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testUpdateWrapper(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.gt("id",1);
        userUpdateWrapper.set("age",80);
        int update = userMapper.update(userUpdateWrapper);
        System.out.println(update);
    }

    @Test
    public void testLambdaWrapper(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.gt(User::getAge,18);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
//        users.forEach(System.out::println);
        System.out.println(users);
    }

    @Test
    public void testMethodMapper(){
        User myUser = userMapper.findMyUser(1L);
        System.out.println(myUser);
    }


}
