package com.easycomp.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * 1、post请求：body里必须包含凭证匹配的字段
 * 2、其他需要匹配权限的请求：获取path的参数
 * 3、可以根据需求，跳过一些请求（OPTIONS），比如公开的请求或者注册登录等
 * @author rensq
 * @create 2020-02-28
 */
public class CustomInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 在请求被处理之前调用
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //todo:自定义拦截
        return true;
    }

    /**
     * 在请求被处理后,视图渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){

    }

    /**
     * 在整个请求结束后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}