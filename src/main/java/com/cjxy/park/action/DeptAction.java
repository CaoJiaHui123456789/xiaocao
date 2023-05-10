package com.cjxy.park.action;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cjxy.park.bean.system.SysDept;
import com.cjxy.park.service.DeptService;


@RestController
@RequestMapping(value = "/dept")
public class DeptAction {
	@Resource
	private DeptService deptService;

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryAllDept")
	public List<SysDept> queryAllDept() {
		try {
			List<SysDept> ls = deptService.queryAll();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//分页
		@RequestMapping(value = "/querDept")
		public Map<String,Object> querDept(SysDept dept,int pageNo,int pageSize){
			Map<String, Object> map = new HashMap<>();
			try {
				Page<SysDept> pg = deptService.queryDept(dept, pageNo, pageSize);
				map.put("rows", pg.getContent());
				map.put("total", pg.getTotalElements());

			} catch (Exception e) {
				e.printStackTrace();
				map.put("rows", null);
				map.put("total", 0);
			}
			return map;
		}

		
		/**
		 * 增加部门
		 * @return
		 */

		@RequestMapping(value = "/addDept")
		public Map<String,Object> addDept(SysDept dept){
			Map<String, Object> map = new HashMap<>();
			try {
				String mess = deptService.saveDept(dept);
					map.put("mess", mess);

			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
		
		/**
		 * 
		 * 修改部门
		 * @return
		 */
		@RequestMapping(value = "/updDept")
		public Map<String,Object> updDept(SysDept dept){
			Map<String,Object> map=new HashMap<>();
			try {
				deptService.updDept(dept);
				map.put("mess", "success");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
		
		/**
		 * 删除部门
		 * @param deptId
		 * @return
		 */

		@RequestMapping(value = "/delDept")
		public Map<String,Object> delDept(Integer deptId){
			Map<String,Object> map=new HashMap<>();
			try {
				deptService.delDept(deptId);
				map.put("mess", "success");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}	

}