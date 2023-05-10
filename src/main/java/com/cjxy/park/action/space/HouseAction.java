package com.cjxy.park.action.space;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjxy.park.bean.space.SysAptDiagram;
import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysHouse;
import com.cjxy.park.service.space.HouseService;

@RestController
@RequestMapping(value = "/sysset")
public class HouseAction {
	@Resource
	private HouseService houseService;

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryAllHouse")
	public Map<String, Object> queryAllHouse() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<SysHouse> ls = houseService.queryAll();
			map.put("rows", ls);
			map.put("total", ls.size());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
	}

	// 分页
	@RequestMapping(value = "/queryHouse")
	public Map<String, Object> querHouse(SysHouse house, int pageNo, int pageSize ) {
		Map<String, Object> map = new HashMap<>();
		try {
			Page<SysHouse> pg = houseService.queryHouse(house, pageNo, pageSize);
			map.put("rows", pg.getContent());
			map.put("total", pg.getTotalElements());

		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
	}

	// 增加房间
	@RequestMapping(value = "/addHouse")
	public Map<String, Object> addHouse(SysHouse house, SysBuilding building,SysAptDiagram aptdiagram) {
		house.setBuilding(building);
		house.setAptdiagram(aptdiagram);
		Map<String, Object> map = new HashMap<>();
		try {
			String mess = houseService.saveHouse(house);
			
				map.put("mess", mess);

		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}

	// 修改房间
	@RequestMapping(value = "/updHouse")
	public Map<String, Object> updHouse(SysHouse house, SysBuilding building,SysAptDiagram aptdiagram) {
		house.setBuilding(building);
		house.setAptdiagram(aptdiagram);
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("mess", houseService.updHouse(house));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}

	// 删除房间
	@RequestMapping(value = "/delHouse")
	public Map<String, Object> delHouse(Integer houseId) {
		Map<String, Object> map = new HashMap<>();
		try {
			houseService.delHouse(houseId);
			map.put("mess", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}
	
	//加载图片
	@RequestMapping(value="/picture/park/{floder}/{name}",produces="image/jpeg")
	public byte[] getPicture(HttpServletRequest request,@PathVariable("floder") String floder,@PathVariable("name") String name){
		String requestURL = request.getRequestURL().toString();
		String path=floder+"/"+name;
		byte[] data;
		try {
			data=houseService.downloadFile("park", path);
		}catch(Exception e){
			System.out.println(e);
			data=null;
		}
		return data;
		
	}
}

    
