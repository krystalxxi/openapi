package com.ecommerce.openapi.core.protocol.param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ecommerce.openapi.api.register.dto.RestApiInfoDto;
import com.ecommerce.openapi.core.protocol.enums.InvokeTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class RequestParam {
    /**
     * 调用rpc服务全路径名
     */
    private String invokeService;
    /**
     * rpc服务版本号
     */
    private String invokeServiceVersion;
    /**
     * 调用方法
     */
    private String invokeMethod;
    /**
     * 入参，填写入参全路径名，如com.ecommerce.basicplatform.vo.PageVo
     */
    private String inputArguments;
    /**
     * 出参，填写出参全路径名
     */
    private String outputArgument;
    /**
     * 页面传参
     */
    private String param;

    public RequestParam() {

    }

    public RequestParam(RestApiInfoDto restApiInfoDto, JSONObject jsonObject) {
        this.setInvokeService(restApiInfoDto.getServiceName());
        this.setInvokeMethod(restApiInfoDto.getMethodName());
        if (StringUtils.isNotEmpty(restApiInfoDto.getInputArgument())) {
            JSONArray inputObject = JSONObject.parseArray(restApiInfoDto.getInputArgument());
            if (null != inputObject && !inputObject.isEmpty()){
                StringBuilder stringBuilder = new StringBuilder();
                inputObject.forEach(o -> {
                    JSONObject object = (JSONObject) o;
                    if (object.containsKey("fieldType")){
                        stringBuilder.append(object.getString("fieldType")).append(",");
                    }
                });
                this.setInputArguments(stringBuilder.substring(0,stringBuilder.length()-1));
            }
        }
        JSONObject outObject = JSONObject.parseObject(restApiInfoDto.getOutputArgument());
        if (null != outObject && outObject.containsKey("fieldType")) {
            this.setOutputArgument(outObject.getString("fieldType"));
        }
        if (null != jsonObject){
            this.setParam(jsonObject.toJSONString());
        }
    }

    public String formatUrl() {
        StringBuilder stringBuilder = new StringBuilder("dubbo://localhost:80?type=").append(InvokeTypeEnum.DUBBO.getName())
                .append("&invokeService=").append(this.getInvokeService())
                .append("&version=").append(this.getInvokeServiceVersion())
                .append("&invokeMethod=").append(this.getInvokeMethod())
                .append("&inputArguments=").append(this.getInputArguments())
                .append("&param=").append(this.param);
        return stringBuilder.toString();
    }


}
