package com.boot;

import com.boot.dao.UserMapper;
import com.boot.entity.User;
import com.boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author youzhengjie 2022-08-16 00:05:12
 */
@SpringBootTest
public class MybatisPlusCrud {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //插入操作
    @Test
    void testInsert() {

        User user = new User();
        // mybatis-plus默认会为实体类为id的字段通过雪花算法生成分布式id，所以不用set id。
        user.setName("张三")
                .setAge(15)
                .setEmail("1550324080@qq.com");

        //相当于INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int res = userMapper.insert(user);

        System.out.println("受影响行数："+res);
        System.out.println("mybatis-plus自动生成的id："+user.getUid());

    }

    //通过id删除记录
    @Test
    void deleteById(){

        //删除id为2L的数据
        //相当于DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(2L);
        System.out.println("受影响行数："+result);

    }

    //通过id批量删除记录
    @Test
    void BatchDeleteByIds(){
        //通过多个id批量删除
        //相当于DELETE FROM user WHERE id IN ( ? , ? , ? )
        int result = userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println("受影响行数："+result);

    }

    //通过map条件删除记录
    @Test
    void testDeleteByMap(){

        //根据map集合中所设置的条件删除记录
        //DELETE FROM user WHERE name = ? AND age = ?
        Map<String,Object> hashMap=new HashMap<>();
        hashMap.put("age", 21);
        hashMap.put("name", "Sandy");

        int result = userMapper.deleteByMap(hashMap);
        System.out.println("受影响行数："+result);

    }

    //修改操作
//    @Test
//    void update(){
//
//        //UPDATE user SET name=?, age=? WHERE id=?
//
//        User user = new User(5L,"新名字",20,null);
//
//        int result = userMapper.updateById(user);
//        System.out.println("受影响行数："+result);
//
//    }

    //根据id查询用户信息
    @Test
    void selectUserById(){

        //根据id查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?

        User user = userMapper.selectById(2L);

        System.out.println(user);

    }

    //根据多个id查询多个用户信息
    @Test
    void BatchSelectUserByIds(){

        //根据多个id查询多个用户信息
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));

        users.forEach(System.out::println);

    }

    //通过map条件查询用户信息
    @Test
    void selectUserByMap(){

        //通过map条件查询用户信息
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?

        Map<String,Object> hashmap=new HashMap<>();
        hashmap.put("age", 28);
        hashmap.put("name", "Tom");

        List<User> users = userMapper.selectByMap(hashmap);

        users.forEach(System.out::println);
    }

    //查询所有数据
    @Test
    void selectAllUsers(){

        //查询所有用户信息
        //SELECT id,name,age,email FROM user

        List<User> users = userMapper.selectList(null);

        users.forEach(System.out::println);
    }

    //----------service


    //测试查询总记录数
    @Test
    void selectUserCount(){

        long count = userService.count();

        System.out.println("总记录数：" + count);
    }


    //测试批量插入
    @Test
    void BatchInsert(){

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
         User user = new User();
         user.setName("abcde" + i);
         user.setAge(20 + i);
         user.setEmail("1550324080@qq.com");

         users.add(user);

        }

        userService.saveBatch(users);

    }



}
