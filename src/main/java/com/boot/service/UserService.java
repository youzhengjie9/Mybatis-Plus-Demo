package com.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.entity.User;

/**
 * @author youzhengjie 2022-08-16 17:52:58
 */

//1：UserService通过继承IService接口，便可以获得IService接口提供的基础功能
//2：IService的泛型：为我们要操作的实体类
public interface UserService extends IService<User> {


}
