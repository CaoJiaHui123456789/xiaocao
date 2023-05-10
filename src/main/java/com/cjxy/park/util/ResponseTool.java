package com.cjxy.park.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ResponseTool {
	@Resource
	private HttpServletResponse response;
	
	@Value("${server.servlet.context-path:/}")
	private String ctxPath;
	
	@Value("${context-resource:/static}")
	private String resource;
	
	public void redirection(String url) throws IOException {
		response.sendRedirect(ctxPath+resource+url);
	}
}
