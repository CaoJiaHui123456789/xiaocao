package com.cjxy.park.action.space;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysPark;
import com.cjxy.park.service.space.BuildingService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/sysset")
public class BuildingAction {

	@Resource
	private BuildingService buildService;

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryAllBuilding")
	public List<SysBuilding> queryAllBuilding() {
		try {
			List<SysBuilding> ls = buildService.queryAll();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 分页
	@RequestMapping(value = "/queryBuilding")
	public Map<String, Object> querBuilding(SysBuilding build, int pageNo, int pageSize, Double max, Double min) {
		Map<String, Object> map = new HashMap<>();
		try {
			Page<SysBuilding> pg = buildService.queryBuilding(build, pageNo, pageSize,max,min);
			map.put("rows", pg.getContent());
			map.put("total", pg.getTotalElements());

		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
	}

	// 增加楼宇
	@RequestMapping(value = "/addBuilding")
	public Map<String, Object> addBuilding(SysBuilding build, SysPark park) {
		Map<String, Object> map = new HashMap<>();
		try {
			build.setPark(park);
			String mess = buildService.saveBuilding(build);
			
			map.put("mess", mess);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}

	// 修改楼宇
	@RequestMapping(value = "/updBuilding")
	public Map<String, Object> updBuilding(SysBuilding building, SysPark park) {
		building.setPark(park);
		Map<String, Object> map = new HashMap<>();
		try {
			String mess=buildService.updBuilding(building);
			map.put("mess", mess);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}

	// 删除楼宇
	@RequestMapping(value = "/delBuilding")
	public Map<String, Object> delBuilding(Integer buildingId) {
		Map<String, Object> map = new HashMap<>();
		try {
			buildService.delBuilding(buildingId);
			map.put("mess", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}
}
