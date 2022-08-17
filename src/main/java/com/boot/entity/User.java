package com.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author youzhengjie 2022-08-15 18:39:54
 */

// 指定表名
@TableName("t_user")
//lombok注解
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    @TableId(value = "uid") //uid为我们数据库表的主键名
    private Long uid;
    @TableField(value = "username") //username为我们表的字段，此时和实体类的name字段一一对应。
    private String name;
    private Integer age;
    private String email;
    @TableLogic //指定该属性为逻辑删除
    @TableField("is_deleted") //is_deleted为我们表的字段，此时和实体类的isDeleted字段一一对应。
    private int isDeleted;

}
