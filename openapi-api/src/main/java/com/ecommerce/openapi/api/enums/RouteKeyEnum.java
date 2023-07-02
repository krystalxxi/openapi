package com.ecommerce.openapi.api.enums;

/**
 * 终端对应的routeKey
 */
public enum RouteKeyEnum {
    APP(1,"APP"),
    WEB(2,"Web"),
    IOS(3,"Ios");

    /**
     * routekey
     */
    private Integer routeKey;

    /**
     * 终端类型
     */
    private String termType;

    RouteKeyEnum(Integer routeKey,String termType){
        this.routeKey = routeKey;
        this.termType = termType;
    }
}
