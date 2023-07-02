package com.ecommerce.openapi.core.exception;

import lombok.Data;

/**
 * 业务层异常
 */
@Data
public class ApiGatewayBizException extends Exception {
    //异常信息
    public String message;

    public String errorCode;

    public ApiGatewayBizException(String message) {
        super(message);
        this.message = message;
    }

    public ApiGatewayBizException(String errorCode, String message) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
