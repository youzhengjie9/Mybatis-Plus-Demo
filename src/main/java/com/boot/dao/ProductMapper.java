package com.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author youzhengjie 2022-08-17 22:12:21
 */
@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {


}
