package com.ale.app_interceptor.demo_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private  final HandlerInterceptor timeInterceptor;

    @Autowired
    public MvcConfig(@Qualifier("timeInterceptor") HandlerInterceptor timeInterceptor) {
        this.timeInterceptor = timeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
        //para ciertas rutas:
        //registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar","/app/foo");
        //registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/baz");
    }
}
