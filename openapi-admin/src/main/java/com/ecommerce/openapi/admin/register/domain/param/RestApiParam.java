package com.ecommerce.openapi.admin.register.domain.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RestApiParam implements Serializable {
    private String appName;
    private String restUrl;
    private String serviceName;
    private String methodName;
    private List<ArgumentParam> inputArgument;
    private ArgumentParam outputArgument;
    private Integer state;
    private Integer routeKey;
}
