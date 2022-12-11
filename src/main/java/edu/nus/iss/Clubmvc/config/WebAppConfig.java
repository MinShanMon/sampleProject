package edu.nus.iss.Clubmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.nus.iss.Clubmvc.interceptor.SecurityInterceptor;

@Component
public class WebAppConfig implements WebMvcConfigurer{
    @Autowired
    SecurityInterceptor securityInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(securityInteceptor);
    }
}
