package com;

import com.alibaba.fastjson.parser.ParserConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.module.system.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
        // 序列化
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

}
