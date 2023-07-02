package com.ecommerce.openapi.core.protocol.filter;

import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CacheBodyGlobalFilter implements WebFilter, Ordered {
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 把Body体内容放进ServerHttpRequestDecorator中在过滤器链中传递下去
     *
     * @param webExchange
     * @param webFilterChain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange webExchange, WebFilterChain webFilterChain) {
        if (HttpMethod.POST.equals(webExchange.getRequest().getMethod())
                && webExchange.getRequest().getHeaders().getContentType() != null
                && webExchange.getRequest().getHeaders().getContentLength() > 0L) {
            return DataBufferUtils.join(webExchange.getRequest().getBody()).flatMap(dataBuffer -> {
                DataBufferUtils.retain(dataBuffer);
                Flux<DataBuffer> cacheFlux = Flux.defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
                ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(webExchange.getRequest()) {
                    @Override
                    public Flux<DataBuffer> getBody() {
                        return cacheFlux;
                    }
                };
                return webFilterChain.filter(webExchange.mutate().request(mutatedRequest).build());
            });
        }
        return webFilterChain.filter(webExchange);
    }
}

