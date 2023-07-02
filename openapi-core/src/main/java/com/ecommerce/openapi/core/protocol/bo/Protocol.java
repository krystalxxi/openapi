package com.ecommerce.openapi.core.protocol.bo;

import lombok.Data;

@Data
public class Protocol<T> {
    T invoker;

    public Protocol(T invoker) {
        this.invoker = invoker;
    }

}
