package com.delivery.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

public class TestServiceConfig {

    @Bean(name = "mvcHandlerMappingIntrospector")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
}
