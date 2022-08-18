package com.boot;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.dao.ProductMapper;
import com.boot.dao.UserMapper;
import com.boot.entity.Product;
import com.boot.entity.User;
import com.boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class MybatisPlusPlugins {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductMapper productMapper;

    @Test
    void testPage01(){

        //例如：total（总的数据记录数）=10，size（每一页的记录数大小）=5
        //那么就可以分成（10/5=2）页，分别是第1页和第2页，current就是当前页，因为只有2页，所以current只能写1或者2.

        //SELECT uid,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 LIMIT ?,?

        Page<User> userPage = new Page<>(2,5);

        //第一种写法：
//        userMapper.selectPage(userPage,null);

        //第二种写法：
        userService.page(userPage,null);

        //获取分页数据
        List<User> list = userPage.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页："+userPage.getCurrent());
        System.out.println("每页显示的条数："+userPage.getSize());
        System.out.println("总记录数："+userPage.getTotal());
        System.out.println("总页数："+userPage.getPages());
        System.out.println("是否有上一页："+userPage.hasPrevious());
        System.out.println("是否有下一页："+userPage.hasNext());

    }

    @Test
    void testPage02(){


        Page<User> userPage = new Page<>(1,5);

        //调用刚刚我们自定义的方法
        //select * from t_user where age=? LIMIT ?

        userMapper.selectPageVo(userPage,20);

        //获取分页数据
        List<User> list = userPage.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页："+userPage.getCurrent());
        System.out.println("每页显示的条数："+userPage.getSize());
        System.out.println("总记录数："+userPage.getTotal());
        System.out.println("总页数："+userPage.getPages());
        System.out.println("是否有上一页："+userPage.hasPrevious());
        System.out.println("是否有下一页："+userPage.hasNext());

    }

    @Test
    void test03(){


        //A用户获取到的
        Product p1 = productMapper.selectById(1L);
        //B用户获取到的
        Product p2 = productMapper.selectById(1L);

        //A用户修改库存
        p1.setNumber(p1.getNumber()+20);
        productMapper.update(p1,null);

        //B用户修改库存
        p2.setNumber(p2.getNumber()+30);
        productMapper.update(p2,null);


    }



}
