package com.cjxy.park.config;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
	/**
     * 在请求被处理之前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截路径:"+request.getRequestURI());
		
		 // 检查每个到来的请求对应的session域中是否有登录标识
        Object userCode = request.getSession().getAttribute("userCode");
        if (null == userCode || !(userCode instanceof String)) {
        	System.out.println("登录超时...");
            // 未登录，重定向到登录页
        	request.getRequestDispatcher("/login?mess=登录超时").forward(request,response);
        	return false;
        }else {
	        String userName = (String) userCode;
	       	System.out.println("当前用户已登录，登录的用户代码为： " +userName);
	        return true;
        }
	}
	/**
     * 在请求被处理后，视图渲染之前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception { 
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	 /**
     * 在整个请求结束后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}

