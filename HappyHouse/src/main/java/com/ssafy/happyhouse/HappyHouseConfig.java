package com.ssafy.happyhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ssafy.happyhouse.controller.SignonInterceptor;

@Configuration
public class HappyHouseConfig extends WebMvcConfigurerAdapter{

   
    @Autowired
    private SignonInterceptor signonInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(signonInterceptor)
//                .addPathPatterns("/qna").addPathPatterns("/move-qna").addPathPatterns("/qna/**")
//        		.addPathPatterns("/notice").addPathPatterns("/move-notice").addPathPatterns("/notice/**")
//        		.addPathPatterns("/move-housedeal")
//        		.addPathPatterns("/move-news");
    }
 
}