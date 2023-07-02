package com.ecommerce.openapi.core.protocol.service.impl;

import com.ecommerce.openapi.core.protocol.bo.Protocol;
import com.ecommerce.openapi.core.protocol.param.RequestParam;
import com.ecommerce.openapi.core.protocol.service.ProtocolConvertService;
import com.ecommerce.openapi.core.protocol.util.DubboProtocolUtil;
import org.apache.dubbo.common.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * rest服务转换dubbo服务
 */
//@Service("dubbo")
@Service
public class ConvertToDubboService implements ProtocolConvertService {
    @Autowired
    DubboProtocolUtil dubboSupportUtil;

    /**
     * dubbo服务调用
     * @param requestParam
     * @return
     * @throws Exception
     */
    @Override
    public Object invoke(RequestParam requestParam) throws Exception {
        Protocol<URL> protocol = dubboSupportUtil.convert(requestParam);
        return dubboSupportUtil.invoke(protocol.getInvoker());
    }

}
