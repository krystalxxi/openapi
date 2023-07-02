package com.ecommerce.openapi.admin.register.domain.bo;

import lombok.Data;

@Data
public class AppInfoBo {
    private String appName;

    private String jarName;

    private String jarVersion;
}
