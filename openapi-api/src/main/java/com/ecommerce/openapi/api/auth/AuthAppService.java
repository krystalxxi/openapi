package com.ecommerce.openapi.api.auth;

/**
 * 鉴权
 */
public interface AuthAppService {
    /**
     * 校验session是否过期
     *
     * @param sessionId
     * @return
     */
    boolean checkSession(String sessionId);
}
