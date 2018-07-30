package com.summary.zkhdsummary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.summary.zkhdsummary.mapper")
public class ZkhdsummaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkhdsummaryApplication.class, args);
    }
}
