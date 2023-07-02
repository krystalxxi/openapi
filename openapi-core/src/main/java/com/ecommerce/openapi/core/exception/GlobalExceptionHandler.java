package com.ecommerce.openapi.core.exception;

import com.ecommerce.basicplatform.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiGatewayBizException.class)
    public ResultVo exceptionHandler(ApiGatewayBizException e){
        return ResultVo.newExceptionResult("-100",e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVo exceptionHandler(Exception e) {
        return ResultVo.newExceptionResult("-110", "操作失败");
    }

}
