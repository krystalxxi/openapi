package com.ecommerce.openapi.core.protocol.enums;

public enum InvokeTypeEnum {
    DUBBO(1, "dubbo");
    Byte code;
    String name;

    InvokeTypeEnum(Integer code, String name) {
        this.code = code.byteValue();
        this.name = name;
    }

    public static String getNameByCode(Byte code){
        InvokeTypeEnum[] enums = InvokeTypeEnum.values();
        for (InvokeTypeEnum typeEnum:enums){
            if (typeEnum.getCode() == code){
                return typeEnum.getName();
            }
        }
        return DUBBO.getName();
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
