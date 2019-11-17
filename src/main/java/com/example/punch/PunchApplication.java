package com.example.punch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zzs
 * @date 2019/10/24 16:00
 */
@SpringBootApplication
@PropertySource(value = {"classpath:config.properties"}, ignoreResourceNotFound = true)
@MapperScan(basePackages = "com.example.punch.dao")
public class PunchApplication {

    public static void main(String[] args) {
        //开启log日志彩色显示
        System.setProperty("log4j.skipJansi", "false");

        SpringApplication.run(PunchApplication.class, args);
    }

}

