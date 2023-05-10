package com.cjxy.park.action;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjxy.park.util.ResponseTool;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/")
public class IndexAction {
	@Resource
	private ResponseTool tool;

	@RequestMapping(value = "/login")
	public void gotoLoginPage() throws IOException {
		tool.redirection("/admin/login.html");
	}
	@RequestMapping(value = "/logout")
	public void gotoLogoutPage(HttpSession session) throws IOException {
		session.removeAttribute("userCode");
		tool.redirection("/admin/login.html");
	}
	
	@RequestMapping(value = "/index")
	public void gotoIndex() throws IOException {
		tool.redirection("/admin/index.html");
	}
	@RequestMapping(value = "/ApartDiagram")
	public void gotoParkMannage() throws IOException {
		tool.redirection("/admin/space/ApartDiagram.html");
	}
	

}
