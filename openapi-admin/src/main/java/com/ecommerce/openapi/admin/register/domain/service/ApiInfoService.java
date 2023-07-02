package com.ecommerce.openapi.admin.register.domain.service;

import com.ecommerce.openapi.admin.register.domain.bo.ApiInfoBo;

import java.util.List;

/**
 * api列表查询
 */
public interface ApiInfoService {
    List<ApiInfoBo> getApiInfo(String appName);
}
