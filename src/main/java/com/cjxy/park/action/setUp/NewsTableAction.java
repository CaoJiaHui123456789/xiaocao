package com.cjxy.park.action.setUp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjxy.park.bean.setUp.SysNewsTable;

import com.cjxy.park.service.setUp.NewsTableService;

@RestController
@RequestMapping(value = "/sysset")
public class NewsTableAction {
	@Resource
	private NewsTableService newsTableService;
	
	
	//查询所有
	@RequestMapping(value = "/queryAllNewsTable")
	public List<SysNewsTable> queryAllNewsTable() {
		try {
			List<SysNewsTable> ls = newsTableService.queryAll();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 分页
	@RequestMapping(value = "/queryNewsTable")
	public Map<String, Object> querNewsTable(SysNewsTable table, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		try {
			Page<SysNewsTable> pg = newsTableService.queryNewsTable(table, pageNo, pageSize);
			map.put("rows", pg.getContent());
			map.put("total", pg.getTotalElements());

		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
	}
	
	//增加信息
	@RequestMapping(value = "/addNewsTable")
	public Map<String, Object> addNewsTable(SysNewsTable table) {
		Map<String, Object> map = new HashMap<>();
		try {
			String mess = newsTableService.saveNewsTable(table);
			
			map.put("mess", mess);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}
	
	// 修改信息
		@RequestMapping(value = "/updNewsTable")
		public Map<String, Object> updNewsTable(SysNewsTable table) {
			Map<String, Object> map = new HashMap<>();
			try {
				String mess=newsTableService.updNewsTable(table);
				map.put("mess", mess);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}

      //删除信息
		@RequestMapping(value = "/delNewsTable")
		public Map<String, Object> delNewsTable(Integer newsId) {
			Map<String, Object> map = new HashMap<>();
			try {
				newsTableService.delNewsTable(newsId);
				map.put("mess", "success");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
	}
