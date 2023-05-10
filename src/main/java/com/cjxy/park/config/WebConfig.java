package com.cjxy.park.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebConfig extends WebMvcConfigurationSupport{
	

	@Value("${context-resource:/static}")
	private String resource;
	
	
	 @Override 
	 public void addInterceptors(InterceptorRegistry registry) {
		 InterceptorRegistration addInterceptor = registry.addInterceptor(new
		 LoginInterceptor()); 
		 // 排除配置 
		 addInterceptor.excludePathPatterns("/login");
		 addInterceptor.excludePathPatterns("/sysset/queryByUserCode");
		 addInterceptor.excludePathPatterns("/busi/**");
		 addInterceptor.excludePathPatterns("/error");
		 addInterceptor.excludePathPatterns("/static/**");//排除静态资源
		 addInterceptor.addPathPatterns("/**");
	 
	 }
	
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler(resource+"/**").addResourceLocations("classpath:/static/");//
	}
	

}
