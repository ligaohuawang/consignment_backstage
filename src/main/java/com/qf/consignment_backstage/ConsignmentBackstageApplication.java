package com.qf.consignment_backstage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf")
@MapperScan("com.qf.mapper")
public class ConsignmentBackstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsignmentBackstageApplication.class, args);
    }

}
