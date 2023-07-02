package com.ecommerce.openapi.admin.register.domain.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class RestApiBo {
    private long id;
    private String restUrl;
    private String serviceName;
    private String methodName;
    private String inputArgument;
    private String outputArgument;
    private String appName;
    /**
     * 用于区分接口对应的终端
     */
    private Integer routeKey;
}
