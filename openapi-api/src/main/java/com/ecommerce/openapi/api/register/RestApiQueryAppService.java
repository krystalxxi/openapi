package com.ecommerce.openapi.api.register;

import com.ecommerce.openapi.api.register.dto.RestApiInfoDto;

/**
 * api查询服务
 */
public interface RestApiQueryAppService {
    RestApiInfoDto getRestApiInfo(String url) throws Exception;
}
