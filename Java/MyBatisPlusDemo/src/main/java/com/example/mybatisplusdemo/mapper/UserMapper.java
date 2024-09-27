package com.example.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplusdemo.domian.User;

public interface UserMapper extends BaseMapper<User> {
    User findMyUser(Long id);
}
