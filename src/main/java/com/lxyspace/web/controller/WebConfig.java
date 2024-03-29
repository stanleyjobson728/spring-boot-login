package com.lxyspace.web.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    //第一种实现方法：编写addViewControllers方法
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //将login.html映射到路径urlpath为："/"上
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //第一种方法：直接编写实现方法
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //排除"/"下的全部路径，除了"/login.html","/","/user/login"
//        registry.addInterceptor(new intercetorConfig()).addPathPatterns("/**")
//                .excludePathPatterns("/login.html", "/", "/user/login",
//                        "/static/**");
//    }

    //    解决静态资源文件夹被拦截
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    //    第二种实现方法：添加WebMvcConfigurer组件
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //排除"/"下的全部路径，除了"/login.html","/","/user/login"
                registry.addInterceptor(new intercetorConfig())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/login.html", "/",
                                "/user/login", "/static/**");
            }

            @Override
            public void addResourceHandlers(
                    ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/static/");
                WebMvcConfigurer.super.addResourceHandlers(registry);
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //将login.html映射到路径urlpath为："/"上
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html")
                        .setViewName("login");
                registry.addViewController("/main.html")
                        .setViewName("dashboard");


            }
        };

        return adapter;
    }

    /**
     * 跨域支持
     *
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .maxAge(3600 * 24);
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(
//                new SessionHandlerInterceptor());
//        interceptorRegistration.excludePathPatterns("/error");
//        interceptorRegistration.excludePathPatterns("/static/**");
//        interceptorRegistration.excludePathPatterns("/login");
//        interceptorRegistration.excludePathPatterns("user/login");
//        interceptorRegistration.addPathPatterns("/**");
//    }
//
//    private class SessionHandlerInterceptor implements HandlerInterceptor {
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//
//            Object user = request.getSession().getAttribute("user");
//            if (user == null) {
//                try {
//                    response.sendRedirect("/login");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return false;
//
//            }
//            return true;
//        }
//    }


//第一种实现方法：编写addViewControllers方法
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        //将login.html映射到路径urlpath为："/"上
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/login.html").setViewName("login");
//        registry.addViewController("/main.html").setViewName("dashboard");
//    }

//第一种方法：直接编写实现方法
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //排除"/"下的全部路径，除了"/login.html","/","/user/login"
//        registry.addInterceptor(new intercetorConfig()).addPathPatterns("/**")
//                .excludePathPatterns("/login.html", "/", "/user/login",
//                        "/static/**");
//    }
//    public void addInterceptors(InterceptorRegistry registry) {
//        //排除"/"下的全部路径，除了"/login.html","/","/user/login"
//        registry.addInterceptor(new intercetorConfig())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login.html", "/",
//                        "/user/login", "/static/**");
//    }

//    解决静态资源文件夹被拦截
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }

//    第二种实现方法：添加WebMvcConfigurer组件
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        WebMvcConfigurer adapter = new WebMvcConfigurer() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                //排除"/"下的全部路径，除了"/login.html","/","/user/login"
//                registry.addInterceptor(new intercetorConfig())
//                        .addPathPatterns("/**")
//                        .excludePathPatterns("/login.html", "/",
//                                "/user/login", "/static/**");
//            }
//
//            @Override
//            public void addResourceHandlers(
//                    ResourceHandlerRegistry registry) {
//                registry.addResourceHandler("/static/**")
//                        .addResourceLocations("classpath:/static/");
//                WebMvcConfigurer.super.addResourceHandlers(registry);
//            }
//
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                //将login.html映射到路径urlpath为："/"上
//                registry.addViewController("/").setViewName("login");
//                registry.addViewController("/login.html")
//                        .setViewName("login");
//                registry.addViewController("/main.html")
//                        .setViewName("dashboard");
//            }
//        };
//
//        return adapter;
//    }
}

