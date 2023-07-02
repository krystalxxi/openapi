package com.ecommerce.openapi.admin.register.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ecommerce.openapi.admin.register.domain.bo.RestApiBo;
import com.ecommerce.openapi.admin.register.domain.param.RestApiParam;
import com.ecommerce.openapi.admin.register.domain.repository.RestApiInfoRepository;
import com.ecommerce.openapi.admin.register.domain.service.RestApiRegisterService;
import com.ecommerce.openapi.admin.infrastructure.po.RestApiInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * api接口注册服务
 */
@Service
public class RestApiRegisterServiceImpl implements RestApiRegisterService {
    @Autowired
    private RestApiInfoRepository restApiInfoRepository;

    @Override
    public boolean register(RestApiParam param) {
        RestApiInfo restApiInfo = new RestApiInfo();
        BeanUtils.copyProperties(param,restApiInfo);
        if (!CollectionUtils.isEmpty(param.getInputArgument())) {
            restApiInfo.setInputArgument(JSON.toJSONString(param.getInputArgument()));
        }
        if (null != param.getOutputArgument()) {
            restApiInfo.setOutputArgument(JSON.toJSONString(param.getOutputArgument()));
        }
        return restApiInfoRepository.save(restApiInfo);
    }

    @Override
    public RestApiBo getService(RestApiParam param) {
        RestApiBo restApiBo = null;
        QueryWrapper<RestApiInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rest_url", param.getRestUrl());
        RestApiInfo restApiInfo = restApiInfoRepository.getOne(queryWrapper);
        if (null != restApiInfo) {
            restApiBo = new RestApiBo();
            BeanUtils.copyProperties(restApiInfo, restApiBo);
        }
        return restApiBo;
    }
}
