
package com.webdemo.config;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登陆成功有用户的session；
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser==null)
        {
            request.setAttribute("msg","没有权限，请先登录！！！");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
        else {
            return true;
        }

    }

}
