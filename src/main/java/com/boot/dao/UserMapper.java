package com.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author youzhengjie 2022-08-15 18:42:11
 */

@Mapper
@Repository
// BaseMapper的泛型就是我们需要操作的实体类User
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据年龄查询用户列表，分页显示
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页（注意page必须放在第一位）
     * @param age 年龄
     */

    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);



}
