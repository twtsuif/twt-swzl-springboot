package com.suif;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.suif.mapper")
public class TwtFindApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwtFindApplication.class, args);
    }

}
