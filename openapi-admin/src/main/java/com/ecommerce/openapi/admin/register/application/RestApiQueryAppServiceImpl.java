package com.ecommerce.openapi.admin.register.application;

import com.ecommerce.openapi.api.register.RestApiQueryAppService;
import com.ecommerce.openapi.api.register.dto.RestApiInfoDto;
import com.ecommerce.openapi.admin.register.domain.bo.RestApiBo;
import com.ecommerce.openapi.admin.register.domain.param.RestApiParam;
import com.ecommerce.openapi.admin.register.domain.service.RestApiRegisterService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class RestApiQueryAppServiceImpl implements RestApiQueryAppService {
    @Autowired
    private RestApiRegisterService restApiRegisterService;

    @Override
    public RestApiInfoDto getRestApiInfo(String url) throws Exception {
        RestApiParam restApiParam = new RestApiParam();
        restApiParam.setRestUrl(url);
        RestApiBo restApiBo = restApiRegisterService.getService(restApiParam);
        RestApiInfoDto restApiInfoDto = new RestApiInfoDto();
        BeanUtils.copyProperties(restApiBo, restApiInfoDto);
        return restApiInfoDto;
    }
}
