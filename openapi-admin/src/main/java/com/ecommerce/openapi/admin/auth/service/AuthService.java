package com.ecommerce.openapi.admin.auth.service;

/**
 * 鉴权服务
 */
public interface AuthService {
    /**
     * 校验session是否过期
     *
     * @param sessionId
     * @return
     */
    boolean checkSession(String sessionId);
}
