package com.ssafy.happyhouse.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.happyhouse.model.dto.AccountDto;

@Component
public class SignonInterceptor implements HandlerInterceptor{
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        AccountDto dto = (AccountDto) session.getAttribute("user_session");

      
        if(ObjectUtils.isEmpty(dto)){
        	PrintWriter out = response.getWriter();
			out.println("<script charset='UTF-8'>alert('Please, Login.'); history.go(-1);</script>");
			out.flush();
            return false;
        }else{
            return true;
        }
        
    }
}