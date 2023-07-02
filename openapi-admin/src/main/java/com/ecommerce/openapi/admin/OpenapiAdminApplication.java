package com.ecommerce.openapi.admin;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
@MapperScan("com.ecommerce.openapi.admin.infrastructure.mapper")
public class OpenapiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiAdminApplication.class, args);
    }

}
