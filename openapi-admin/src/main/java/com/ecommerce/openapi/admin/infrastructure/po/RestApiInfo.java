package com.ecommerce.openapi.admin.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "t_rest_api_info")
@Data
public class RestApiInfo {
    private long id;

    @TableField(value = "rest_url")
    private String restUrl;

    @TableField(value = "service_name")
    private String serviceName;

    @TableField(value = "method_name")
    private String methodName;

    @TableField(value = "input_argument")
    private String inputArgument;

    @TableField(value = "output_argument")
    private String outputArgument;

    @TableField(value = "app_name")
    private String appName;

    @TableField(value = "route_key")
    private Integer routeKey;

}
