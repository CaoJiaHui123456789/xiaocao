package com.cjxy.park.action.setUp;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjxy.park.bean.customer.SysEntryTable;
import com.cjxy.park.bean.setUp.SysNewsCont;
import com.cjxy.park.bean.setUp.SysNewsTable;
import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysHouse;
import com.cjxy.park.bean.space.SysPark;
import com.cjxy.park.service.setUp.IndexSwiperService;
import com.cjxy.park.service.setUp.NewsContService;
import com.cjxy.park.service.setUp.NewsTableService;
import com.cjxy.park.service.space.BuildingService;
import com.cjxy.park.service.space.HouseService;
import com.cjxy.park.service.space.ParkService;

@RestController
@RequestMapping(value="/busi")
public class BusiAction {
	
    @Value(value="${server.servlet.context-path}")
    private String ctxPath;
    @Resource
	private NewsContService newsContService;
    
    @Resource
	private NewsTableService newsTableService;
	
	@Resource
	private ParkService parkService;
	
	@Resource
	IndexSwiperService indexSwiperService;
	
	@Resource
	private BuildingService buildingService;
	
	@Resource
	private HouseService houseService;
	
	
	@RequestMapping(value = "/queryById")
	public Map<String,Object> queryById(Integer newcontId){
		Map<String,Object> map=new HashMap<>();
		try {

			Optional<SysNewsCont> ls=newsContService.queryById(newcontId);
			map.put("rows", ls);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
	}
	@RequestMapping(value="/getImgList")
	public List<String> getPath(){
		return indexSwiperService.getPath();
	}

	@RequestMapping(value="/picture/park/{floder}/{name}",produces="image/jpeg")
	public byte[] getParkpicture(HttpServletRequest request,@PathVariable("floder") String floder,@PathVariable("name") String name){
		String requestURL = request.getRequestURL().toString();
		String path=floder+"/"+name;
		byte[] data;
		try {
			data=parkService.downloadFile("park", path);
		}catch(Exception e){
			System.out.println(e);
			data=null;
		}
		return data;
		
	}
	@RequestMapping(value = "/getAllPark")
	public List<SysPark> getAllPark(){
		List<SysPark> ls;
		try {
			ls=parkService.queryAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			ls=null;
			
		}
		return ls;
	}
	
	
	@RequestMapping(value = "/getAllBuilding")  
	public List<SysBuilding> getAllBuilding(){
		List<SysBuilding> ls;
		try {
			ls=buildingService.queryAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			ls=null;
			
		}
		return ls;
	}
	
	@RequestMapping(value = "/getAllNewsCont")
	public List<SysNewsCont> getAllNewsCont(){
		List<SysNewsCont> ls;
		try {
			ls=newsContService.queryAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			ls=null;
			
		}
		return ls;
	}
	
	@RequestMapping(value = "/querNewsCont")
	public Map<String,Object> querNewsCont(SysNewsCont newsCont,int pageNo,int pageSize ){
		Map<String,Object> map=new HashMap<>();
		try {
			Page<SysNewsCont> pg=newsContService.queryNewsCont(newsCont, pageNo, pageSize);
			map.put("rows", pg.getContent());
			map.put("total",pg.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
	}
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
	
	//查询所有园区
	@RequestMapping(value = "/queryAllPark")
	public List<SysPark> queryAllPark() {
		try {
			List<SysPark> ls = parkService.queryAll();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//查询楼宇
	@RequestMapping(value = "/queryBuilding")
	public List<SysBuilding> queryAllBuilding(Integer parkId) {
		try {
			List<SysBuilding> ls = buildingService.getBuildingByparkId(parkId);
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
    //查询房间
	@RequestMapping(value = "/queryHouse")
	public List<SysHouse> queryAllHouse(Integer buildingId) {
		try {
			List<SysHouse> ls = houseService.getHouseBybuildingId(buildingId);
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}

