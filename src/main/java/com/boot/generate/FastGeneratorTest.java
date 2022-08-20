package com.boot.generate;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author youzhengjie 2022-08-20 00:28:34
 * mybatis-plus代码生成器程序
 */
public class FastGeneratorTest {

    public static void main(String[] args) {

        FastAutoGenerator
                //mysql的数据源配置
                .create("jdbc:mysql://localhost:3306/mybatis_plus-db?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false", "root", "18420163207")
                .globalConfig(builder -> {
                    builder.author("youzhengjie") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://mybatis-plus-generator"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.boot") // 设置父包名
                            .moduleName("gen") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://mybatis-plus-generator")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            //设置需要生成的表名（该表必须要在我们指定的mysql中存在，mybatis-plus才会根据这个表来生成代码）
                            .addInclude("t_user")
                            // 设置过滤表前缀
                            .addTablePrefix("t_", "c_");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute(); //执行

    }

}
