package com.ecommerce.openapi.admin.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "t_app_info")
@Data
public class AppInfo {
    @TableField(value = "app_name")
    private String appName;

    @TableField(value = "jar_name")
    private String jarName;

    @TableField(value = "jar_version")
    private String jarVersion;
}
