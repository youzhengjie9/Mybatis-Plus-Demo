package com.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.dao.UserMapper;
import com.boot.entity.User;
import com.boot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author youzhengjie 2022-08-16 17:57:48
 */

@Service//Service注解
//1：UserServiceImpl 继承与 ServiceImpl<操作的实体类对应的Mapper接口, 操作的实体类>
//2：实现 UserService 接口。
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}
