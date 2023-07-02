package com.ecommerce.openapi.core.protocol.service;

import com.ecommerce.openapi.core.protocol.param.RequestParam;

/**
 * rest服务转换dubbo服务
 */
public interface ProtocolConvertService {

    /**
     * 协议转换
     * @param requestParam
     * @return
     * @throws Exception
     */
    Object invoke(RequestParam requestParam) throws Exception;

}
