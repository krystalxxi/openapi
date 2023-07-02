package com.ecommerce.openapi.api.register.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RestApiInfoDto implements Serializable {
    private long id;
    /**
     * rest url
     */
    private String restUrl;
    /**
     * rpc服务名全路径
     */
    private String serviceName;
    /**
     * rpc服务方法名
     */
    private String methodName;
    /**
     * 入参
     */
    private String inputArgument;
    /**
     * 出参
     */
    private String outputArgument;
    /**
     * 应用名
     */
    private String appName;
    /**
     * rpc服务类型
     */
    private Byte serviceType;
}
