package com.boot;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.boot.dao.UserMapper;
import com.boot.entity.User;
import com.boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusWrapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void test01(){

        //查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)

        //方式1：QueryWrapper
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//
//        queryWrapper.like("username","a")
//                .between("age",20,30)
//                .isNotNull("email");
//
//
//        List<User> users = userMapper.selectList(queryWrapper);
//
//        users.forEach(System.out::println);


        //方式2：QueryChainWrapper
        QueryChainWrapper<User> chainWrapper = userService.query()
                .like("username", "a")
                .between("age", 20, 30)
                .isNotNull("email");

        List<User> users = chainWrapper.list();

        users.forEach(System.out::println);

    }

    @Test
    void test02(){

        //按年龄降序查询用户，如果年龄相同则按id升序排列
        // SELECT uid,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC

        //方式1：QueryWrapper
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("age")
//                .orderByAsc("uid");
//
//        List<User> users = userMapper.selectList(queryWrapper);
//
//        users.forEach(System.out::println);


        //方式2：QueryChainWrapper

        QueryChainWrapper<User> userQueryChainWrapper = userService.query()
                .orderByDesc("age")
                .orderByAsc("uid");
        List<User> userList = userQueryChainWrapper.list();

        userList.forEach(System.out::println);

    }


    @Test
    void test03(){

        //删除email为空的用户
        //DELETE FROM t_user WHERE (email IS NULL)

        //QueryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        userMapper.delete(queryWrapper);


    }

    @Test
    void test04(){

        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        //UPDATE t_user SET age=?, email=? WHERE (username LIKE ? AND (age > ? OR email IS NULL))

        //lambda表达式内的逻辑优先运算
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("username","a")
                .and(i -> i.gt("age", 20).or().isNull("email"));

        User user = new User();
        user.setAge(18);
        user.setEmail("666333@qq.com");
        userMapper.update(user,queryWrapper);

    }


    @Test
    void test05(){

        //查询用户信息的username和age字段
        //SELECT username,age FROM t_user WHERE is_deleted=0

        //方式1：QueryWrapper
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//
//        queryWrapper
//                .select("username","age"); //指定查询的字段（默认是查找全部字段）
//
//        List<User> users = userMapper.selectList(queryWrapper);
//
//        users.forEach(System.out::println);

        //方式2：QueryChainWrapper
        List<User> list = userService
                .query()
                .select("username", "age") //指定查询的字段（默认是查找全部字段）
                .list(); //list方法直接拿到结果集

        list.forEach(System.out::println);

    }

    @Test
    void test06(){

        //查询id小于等于3的用户信息
        //SELECT uid,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid <= 3))

        //方式1：QueryWrapper
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.inSql("uid","select uid from t_user where uid <= 3");
//
//        List<User> users = userMapper.selectList(queryWrapper);
//
//        users.forEach(System.out::println);

        //方式2：QueryChainWrapper
        List<User> userList = userService
                .query()
                .inSql("uid", "select uid from t_user where uid <= 3")
                .list();

        userList.forEach(System.out::println);

    }


    @Test
    void test07()
    {

        //好处：当我们使用了UpdateWrapper时，不需要传入entity了，可以通过set方法修改值

        //UPDATE t_user SET username=?,age=? WHERE is_deleted=0 AND (uid = ?)

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("username","新名字111")
                .set("age",32)
                .eq("uid",5L);

        userMapper.update(null,updateWrapper);

    }


    @Test
    void test08()
    {
        String username=null;
        Integer smallAge=15;
        Integer bigAge=30;

        //SELECT uid,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(username)) { //如果用户名不为空，则添加下面的条件
            queryWrapper
                    .like("username",username);
        }
        if(smallAge != null){
            queryWrapper
                    .ge("age",smallAge);
        }
        if(bigAge != null){
            queryWrapper
                    .le("age",bigAge);
        }


        userMapper.selectList(queryWrapper);

    }

    @Test
    void test09()
    {
        String username=null;
        Integer smallAge=15;
        Integer bigAge=30;

        //SELECT uid,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotBlank(username),"username",username)
                .ge(smallAge!=null,"age",smallAge)
                .le(bigAge!=null,"age",bigAge);

        userMapper.selectList(queryWrapper);

    }

    @Test
    void test10(){
        String username=null;
        Integer smallAge=15;
        Integer bigAge=30;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.like(StringUtils.isNotBlank(username),User::getName,username);
        lambdaQueryWrapper.ge(smallAge!=null,User::getAge,smallAge);
        lambdaQueryWrapper.le(bigAge!=null,User::getAge,bigAge);

        userMapper.selectList(lambdaQueryWrapper);


    }

    @Test
    void test11(){

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
                .set(User::getName,"新名字222")
                .set(User::getAge,55)
                .eq(User::getUid,5L);

        userMapper.update(null,lambdaUpdateWrapper);

    }


}
