package com.ecommerce.openapi.admin.register.domain.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiInfoBo implements Serializable {
    private String serviceName;
    private Class aClass;
    private List<MethodBo> methods;
}
