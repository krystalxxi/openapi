package com.ecommerce.openapi.admin.register.domain.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArgumentBo implements Serializable {
    private String fieldName;
    private Integer index;
    private String fieldType;
    private boolean required = false;
    private List<ArgumentBo> attrs;
}
