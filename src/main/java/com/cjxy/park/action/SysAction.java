package com.cjxy.park.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjxy.park.util.ResponseTool;

@RestController
@RequestMapping(value = "/Sys")
public class SysAction {
	
	@Resource
	private ResponseTool tool;

	@RequestMapping(value = "/goPark")
	public void gotoLoginPage() throws IOException {
		tool.redirection("/admin/sysset/ParkMannage.html");
	}
	

}
