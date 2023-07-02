package com.ecommerce.openapi.core;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关核心通信服务：用于网络通信转换处理，承接http请求，调用RPC服务，责任链模块调用
 */
@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
@EnableCaching
public class OpenapiCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiCoreApplication.class, args);
    }

}
