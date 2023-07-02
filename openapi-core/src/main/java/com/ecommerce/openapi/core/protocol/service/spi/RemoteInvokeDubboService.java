package com.ecommerce.openapi.core.protocol.service.spi;

import com.alibaba.fastjson.JSONObject;

import com.ecommerce.openapi.core.exception.ApiGatewayBizException;
import com.ecommerce.openapi.core.protocol.service.RemoteInvokeService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * 调用远程dubbo服务
 */
public class RemoteInvokeDubboService implements RemoteInvokeService {
    @Override
    public Object invoke(URL url) throws Exception {
        String invokeService = url.getParameter("invokeService");
        String version = url.getParameter("version");
        String invokeMethod = url.getParameter("invokeMethod");
        String inputArguments = url.getParameter("inputArguments");
        String param = url.getParameter("param");

        if (StringUtils.isEmpty(invokeService) || StringUtils.isEmpty(invokeMethod)) {
            throw new ApiGatewayBizException("缺少必填参数");
        }
        JSONObject arg0 = null;
        if (!StringUtils.isEmpty(param)) {
            arg0 = JSONObject.parseObject(param);
        }
        //参数对象
        Object[] objectParams = new Object[1]; //1个参数值
        objectParams[0] = arg0;

        Object result;
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setInterface(invokeService);
        reference.setGeneric(true);
        reference.setTimeout(5000);
        GenericService genericService = reference.get();
        try {
//            result = genericService.$invoke(invokeMethod, new String[]{inputArguments}, new Object[]{arg0});
            result = genericService.$invoke(invokeMethod, new String[]{inputArguments}, objectParams);

        } catch (Exception e) {
            throw new ApiGatewayBizException("调用远程服务失败,{}", e.getMessage());
        }
        return result;
    }
}
