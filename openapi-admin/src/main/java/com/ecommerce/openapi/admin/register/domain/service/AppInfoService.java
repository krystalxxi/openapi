package com.ecommerce.openapi.admin.register.domain.service;

import com.ecommerce.openapi.admin.register.domain.bo.AppInfoBo;

/**
 * APP应用管理
 */
public interface AppInfoService {
    AppInfoBo getAppInfoByName(String appName);
}
