package com.ecommerce.openapi.admin.register.controller;

import com.ecommerce.basicplatform.vo.ResultVo;
import com.ecommerce.openapi.admin.register.domain.bo.ApiInfoBo;
import com.ecommerce.openapi.admin.register.domain.param.RestApiParam;
import com.ecommerce.openapi.admin.register.domain.service.ApiInfoService;
import com.ecommerce.openapi.admin.register.domain.service.RestApiRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiInfoService apiInfoService;

    @Autowired
    private RestApiRegisterService restApiRegisterService;

    @PostMapping("/queryApi")
    public ResultVo<List<ApiInfoBo>> queryApiList(@RequestParam String appName) {
        ResultVo<List<ApiInfoBo>> result = new ResultVo<>();
        result.setData(apiInfoService.getApiInfo(appName));
        return result;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ResultVo<Boolean> registerApi(@RequestBody RestApiParam restApiParam) {
        if (StringUtils.isEmpty(restApiParam.getAppName()) || StringUtils.isEmpty(restApiParam.getMethodName())) {
            return ResultVo.newExceptionResult("-110", "参数错误");
        }
        return ResultVo.newSuccessResult(restApiRegisterService.register(restApiParam));
    }
}
