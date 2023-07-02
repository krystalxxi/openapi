package com.ecommerce.openapi.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecommerce.basicplatform.vo.ResultVo;
import com.ecommerce.openapi.api.auth.AuthAppService;
import com.ecommerce.openapi.api.register.RestApiQueryAppService;

import com.ecommerce.openapi.api.register.dto.RestApiInfoDto;
import com.ecommerce.openapi.core.protocol.param.RequestParam;
import com.ecommerce.openapi.core.protocol.service.ProtocolConvertService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestController
@RequestMapping("/openapi")
public class ProtocolProxyController {

    @DubboReference
    private RestApiQueryAppService restApiQueryAppService;

//    @Autowired
//    @Qualifier(value = "dubbo")
    @Autowired
    private ProtocolConvertService protocolConvertService;

    @DubboReference
    private AuthAppService authAppService;
    private final String URL_PREFIX = "/openapi";

    //    @Async
    @RequestMapping("/**")
    public ResultVo<Object> runMethod(ServerHttpRequest request) throws Exception {
        // 生成traceid
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId",traceId);

        // 鉴权
        if (!checkSession(request)) {
            return ResultVo.newFailResult("登录已失效，请重新登录");
        }
        if (StringUtils.isEmpty(request.getURI().getPath()) || !(request.getURI().getPath().startsWith(URL_PREFIX))) {
            return ResultVo.newFailResult("错误的路径");
        }
        String[] urls = request.getURI().getPath().split(URL_PREFIX);
        if (urls.length < 2) {
            return ResultVo.newFailResult("错误的路径");
        }

        JSONObject jsonObject = getJson(request);
        String restUrl = urls[1];
        RestApiInfoDto restApiInfoBo = restApiQueryAppService.getRestApiInfo(restUrl);
        if (null == restApiInfoBo) {
            return ResultVo.newFailResult("请求服务不存在");
        }
        RequestParam param = new RequestParam(restApiInfoBo, jsonObject);
        Object resultObj = protocolConvertService.invoke(param);
        return ResultVo.newSuccessResult(resultObj);
    }

    private JSONObject getJson(ServerHttpRequest request) throws Exception {
        Flux<DataBuffer> body = request.getBody();
        AtomicReference<JSONObject> jsonObject = new AtomicReference<>();
        body.subscribe(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            DataBufferUtils.release(dataBuffer);
            try {
                String bodyString = new String(bytes, "utf-8");
                jsonObject.set(JSONObject.parseObject(bodyString));
            } catch (Exception e) {
                log.error("error info:{}", e.getMessage());
            }
        });
        return jsonObject.get();
    }

    /**
     * 根据session校验
     *
     * @param request
     * @return
     */
    private boolean checkSession(ServerHttpRequest request) {
        boolean result = Boolean.TRUE;
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if (null == cookies || cookies.isEmpty()) {
            result = Boolean.FALSE;
        } else {
            // todo 校验token
            List<HttpCookie> sessionList = cookies.get("SESSION");
            if (CollectionUtils.isEmpty(sessionList)) {
                result = Boolean.FALSE;
            }
            String session = base64Decode(sessionList.get(0).getValue());
            log.info(session);
            boolean checkFlag = authAppService.checkSession(session);
            if(Boolean.TRUE.equals(checkFlag)){
                result = Boolean.FALSE;
            }
        }
        return result;
    }


    /**
     * Decode the value using Base64.
     *
     * @param base64Value the Base64 String to decode
     * @return the Base64 decoded value
     * @since 1.2.2
     */
    private String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
            return new String(decodedCookieBytes);
        } catch (Exception e) {
            return null;
        }
    }

}
