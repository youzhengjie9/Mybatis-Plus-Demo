package com.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author youzhengjie 2022-08-17 21:56:17
 */

@TableName("t_product")
//lombok注解
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Product implements Serializable {

    @TableId("id")
    private Long id;
    @TableField("name")
    private String name;
    @TableField("number")
    private int number;
    @Version //说明version就是乐观锁标记
    private int version;


}
