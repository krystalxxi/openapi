package com.ecommerce.openapi.admin.register.domain.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MethodBo implements Serializable {
    private String methodName;
    private List<ArgumentBo> inputArguments;
    private ArgumentBo outputArgument;
}
