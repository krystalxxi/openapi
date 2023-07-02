package com.ecommerce.openapi.admin.register.domain.service;


import com.ecommerce.openapi.admin.register.domain.bo.RestApiBo;
import com.ecommerce.openapi.admin.register.domain.param.RestApiParam;

public interface RestApiRegisterService {
    boolean register(RestApiParam param);

    RestApiBo getService(RestApiParam param);
}
