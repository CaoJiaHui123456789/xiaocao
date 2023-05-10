package com.cjxy.park.action.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjxy.park.bean.customer.SysEntryTable;
import com.cjxy.park.service.customer.EntryTableService;

@RestController
@RequestMapping(value = "/customer")
public class EntryTableAction {
	@Resource
	private EntryTableService entryTableService;
	
	//查询所有
	@RequestMapping(value = "/queryAllEntryTable")
	public List<SysEntryTable> queryAllEntryTable() {
		try {
			List<SysEntryTable> ls = entryTableService.queryAll();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	     //分页
			@RequestMapping(value = "/querEntryTable")
			public Map<String,Object> querEntryTable(SysEntryTable entryTable,int pageNo,int pageSize){
				Map<String, Object> map = new HashMap<>();
				try {
					Page<SysEntryTable> pg = entryTableService.queryEntryTable(entryTable, pageNo, pageSize);
					map.put("rows", pg.getContent());
					map.put("total", pg.getTotalElements());

				} catch (Exception e) {
					e.printStackTrace();
					map.put("rows", null);
					map.put("total", 0);
				}
				return map;
			}

		//增加
			@RequestMapping(value = "/addEntryTable")
			public Map<String,Object> addEntryTable(SysEntryTable entryTable){
				Map<String, Object> map = new HashMap<>();
				try {
					String mess = entryTableService.saveEntryTable(entryTable);
						map.put("mess", mess);

				} catch (Exception e) {
					e.printStackTrace();
					map.put("mess", e.toString());
				}
				return map;
			}
			
	    //修改
			@RequestMapping(value = "/updEntryTable")
			public Map<String,Object> updEntryTable(SysEntryTable entryTable){
				Map<String,Object> map=new HashMap<>();
				try {
					entryTableService.updEntryTable(entryTable);
					map.put("mess", "success");
				} catch (Exception e) {
					e.printStackTrace();
					map.put("mess", e.toString());
				}
				return map;
			}
			
		//删除
			@RequestMapping(value = "/delEntryTable")
			public Map<String,Object> delEntryTable(Integer entryId){
				Map<String,Object> map=new HashMap<>();
				try {
					entryTableService.delEntryTable(entryId);
					map.put("mess", "success");
				} catch (Exception e) {
					e.printStackTrace();
					map.put("mess", e.toString());
				}
				return map;
			}	
}
