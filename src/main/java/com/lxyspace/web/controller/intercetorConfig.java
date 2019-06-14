package com.lxyspace.web.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class intercetorConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过getAttribute获取session的值
        Object user = request.getSession().getAttribute("user");
        //判断是否有保存值，有即证明登录成功
        if (user != null) {
            //session不空，表示有保存值，有登陆，放行
            return true;
        } else {
            //没有值，没有登录，返回到登录页面：
//            request.getRequestDispatcher("/login.html").forward(request, response);
            //同时设置错误信息
            request.setAttribute("msg", "没有权限");
            response.sendRedirect("/user/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
