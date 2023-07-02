package com.ecommerce.openapi.admin.auth.application;

import com.ecommerce.openapi.admin.auth.service.AuthService;
import com.ecommerce.openapi.api.auth.AuthAppService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class AuthAppServiceImpl implements AuthAppService {
    @Autowired
    private AuthService authService;

    @Override
    public boolean checkSession(String sessionId) {
        return authService.checkSession(sessionId);
    }
}
