package com.inet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主方法
 * @author HCY
 * @since 2020-11-14
 */
@SpringBootApplication
@MapperScan("com.inet.code.mapper")
public class INetApplication {

    public static void main(String[] args) {
        SpringApplication.run(INetApplication.class, args);
    }

}
