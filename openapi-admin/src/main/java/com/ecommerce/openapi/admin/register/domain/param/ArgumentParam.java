package com.ecommerce.openapi.admin.register.domain.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArgumentParam implements Serializable {
    private String fieldName;
    private String fieldType;
    private List<ArgumentParam> attr;
}
