package com.ecommerce.openapi.core.protocol.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * 远程服务调用
 */
@SPI
public interface RemoteInvokeService {
    /**
     * @return
     */
    @Adaptive({"type"})
    Object invoke(URL url) throws Exception;
}
