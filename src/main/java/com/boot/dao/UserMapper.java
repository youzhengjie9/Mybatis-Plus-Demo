package com.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author youzhengjie 2022-08-15 18:42:11
 */

@Mapper
@Repository
// BaseMapper的泛型就是我们需要操作的实体类User
public interface UserMapper extends BaseMapper<User> {


}
