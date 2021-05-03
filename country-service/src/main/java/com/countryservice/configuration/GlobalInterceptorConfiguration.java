package com.countryservice.configuration;

import com.countryservice.interceptor.LogGrpcInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GlobalInterceptorConfiguration {

    @Bean
    LogGrpcInterceptor logServerInterceptor() {
        return new LogGrpcInterceptor();
    }

}
