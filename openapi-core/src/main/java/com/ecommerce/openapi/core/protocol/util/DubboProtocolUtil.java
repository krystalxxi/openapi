package com.ecommerce.openapi.core.protocol.util;

import com.ecommerce.openapi.core.protocol.bo.Protocol;
import com.ecommerce.openapi.core.protocol.param.RequestParam;
import com.ecommerce.openapi.core.protocol.service.RemoteInvokeService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.stereotype.Component;

@Component
public final class DubboProtocolUtil {
    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    public Object invoke(URL url) throws Exception {
        RemoteInvokeService remoteService = ExtensionLoader.getExtensionLoader(RemoteInvokeService.class).getAdaptiveExtension();
        // todo 异步处理result数据
        return remoteService.invoke(url);
    }

    public Protocol<URL> convert(RequestParam requestParam) throws Exception {
        URL url = URL.valueOf(requestParam.formatUrl());
        return new Protocol(url);
    }
}
