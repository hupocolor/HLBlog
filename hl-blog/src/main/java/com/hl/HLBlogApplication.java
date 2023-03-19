package com.hl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author : hupo
 */
@SpringBootApplication
@MapperScan("com.hl.domain.mapper")
public class HLBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(HLBlogApplication.class,args);
    }
}
