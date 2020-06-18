package com.webdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations and resource handler requests.
     * Interceptors can be registered to apply to all requests or be limited
     * to a subset of URL patterns.
     *
     * @param registry
     */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
       registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/dashboard.html").setViewName("dashboard");
       registry.addViewController("/conlogin.html").setViewName("login");
        registry.addViewController("/signup.html").setViewName("signup");
        registry.addViewController("/analyze/test.html").setViewName("/analyze/test");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login.html",
                        "/css/**","/js/**","/img/**","/conlogin.html",
                        "/login","/layui/**","/images/**","/fonts/**","/signup.html","/signup");
    }
}
