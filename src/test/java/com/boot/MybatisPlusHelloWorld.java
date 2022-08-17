package com.boot;

import com.boot.dao.UserMapper;
import com.boot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author youzhengjie 2022-08-15 18:46:42
 */
@SpringBootTest
public class MybatisPlusHelloWorld {

    @Autowired
    private UserMapper userMapper;

    @Test
    void HelloWorld(){
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);

    }


}
