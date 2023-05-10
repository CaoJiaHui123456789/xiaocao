package com.cjxy.park.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjxy.park.bean.system.SysUser;
import com.cjxy.park.service.UserService;

@RestController
@RequestMapping(value="/sysset")
public class UserAction {
	
		@Resource
		private UserService userService;

		
		/**
		 * 查询所有
		 * @return
		 */
		@RequestMapping(value = "/queryAllUser")
		public Map<String,Object> queryAllUser(){
			Map<String,Object> map=new HashMap<>();
			try {
				List<SysUser> ls=userService.queryAll();
				map.put("rows", ls);
				map.put("total", ls.size());
			} catch (Exception e) {
				e.printStackTrace();
				map.put("rows", null);
				map.put("total", 0);
			}
			return map;
		}
		
		@RequestMapping(value = "/queryByUserCode")
		public Map<String,Object> queryByUserCode(SysUser user,HttpSession session){
			Map<String,Object> map=new HashMap<>();
			try {
				SysUser user2=userService.getUserByCode(user.getUserCode());
				if(user2==null) {
					map.put("mess","用户名或密码错误");
				}else {
					if(user2.getUserPass().equals(user.getUserPass())) {
						
						session.setAttribute("userCode", user2.getUserCode());
						map.put("mess","true");
					}else {
						map.put("mess","用户名或密码错误");
					}
				}
				
				return map;
				
			} catch (Exception e) {
				e.printStackTrace();
				map.put("rows", null);
				map.put("total", 0);
			}
			return map;
		}
		
		@RequestMapping(value = "/querUser")
		public Map<String,Object> querUser(SysUser user,int pageNo,int pageSize ){
			Map<String,Object> map=new HashMap<>();
			try {
				org.springframework.data.domain.Page<SysUser> pg=userService.queryUser(user, pageNo, pageSize);
				map.put("rows", pg.getContent());
				map.put("total",pg.getTotalElements());
				
			} catch (Exception e) {
				e.printStackTrace();
				map.put("rows", null);
				map.put("total", 0);
			}
			return map;
		}
		
		
		/**
		 * 增加
		 * @return
		 */
		
		@RequestMapping(value = "/addUser")
		public Map<String,Object> addUser(SysUser user){
			System.out.println(user);
			Map<String,Object> map=new HashMap<>();
			try {
				int userid=userService.saveUser(user);
				if (userid>0) {
					map.put("mess", "success");
				}else {
					map.put("mess", "保存失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
		
		@RequestMapping(value = "/updUser")
		public Map<String,Object> updUser(SysUser user){
			Map<String,Object> map=new HashMap<>();
			try {
				userService.updUser(user);
				map.put("mess", "success");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
		
		
		@RequestMapping(value = "/delUser")
		public Map<String,Object> delUser(int userId){
			Map<String,Object> map=new HashMap<>();
			try {
				System.out.print(userId);
				userService.delUser(userId);
				map.put("mess", "success");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
		
		
		

}


