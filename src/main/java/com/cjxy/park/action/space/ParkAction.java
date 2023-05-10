package com.cjxy.park.action.space;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjxy.park.bean.space.SysPark;
import com.cjxy.park.service.space.ParkService;



@RestController
@RequestMapping(value="/sysset")
public class ParkAction {
	
	    @Value(value="${server.servlet.context-path}")
	    private String ctxPath;
	
		@Resource
		private ParkService parkService;

//		@RequestMapping(value="/goAllpark")
//		public ModelAndView goPark() {
//		  ModelAndView mod=new ModelAndView("/admin/parkManager.html");
//		   return mod;
//		}	
		
		/**
		 * 查询所有
		 * @return
		 */
		@RequestMapping(value = "/queryAllPark")
		public Map<String,Object> queryAllPark(){
			Map<String,Object> map=new HashMap<>();
			try {
				List<SysPark> ls=parkService.queryAll();
				map.put("rows", ls);
				map.put("total", ls.size());
			} catch (Exception e) {
				e.printStackTrace();
				map.put("rows", null);
				map.put("total", 0);
			}
			return map;
		}
		
		@RequestMapping(value = "/querPark")
		public Map<String,Object> querStaff(SysPark park,int pageNo,int pageSize ){
			Map<String,Object> map=new HashMap<>();
			try {
			
				Page<SysPark> pg=parkService.queryPark(park, pageNo, pageSize);
				System.out.println("park");
				park.toString();
				map.put("rows", pg.getContent());
				map.put("total",pg.getTotalElements());
				
			} catch (Exception e) {
				e.printStackTrace();
				map.put("rows", null);
				map.put("total", 0);
			}
			return map;
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
		
		/**
		 * 增加
		 * @return
		 */
		
		@RequestMapping(value = "/addPark")
		public Map<String,Object> addStaff(SysPark park){
			Map<String,Object> map=new HashMap<>();
			
			try {
				String mess = parkService.saveSysPark(park);
				map.put("mess", mess);
				List<SysPark> ls=parkService.queryAll();
				if(ls.isEmpty()) {
					String parkid=parkService.saveSysPark(park);
					if (parkid == null) {
						map.put("mess", "success");
					}else {
						map.put("mess", "保存失败！");
					}
				}else {
					map.put("mess", "园区已达上限");
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
		
		@RequestMapping(value = "/updSysPark")
		public Map<String,Object> updStaff(SysPark park){
			Map<String,Object> map=new HashMap<>();
			try {
				parkService.updSysPark(park);
				map.put("mess", "success");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
		
		
		@RequestMapping(value = "/delSysPark")
		public Map<String,Object> delSysPark(Integer parkId){
			Map<String,Object> map=new HashMap<>();
			try {
				System.out.print(parkId);
				parkService.delSysPark(parkId);
				map.put("mess", "success");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mess", e.toString());
			}
			return map;
		}
		
		@RequestMapping(value = "/addNewsPicture")
		public Map<String,Object> addPicture(MultipartFile imgFile,String dir){
			Map<String,Object> map=new HashMap<>();
			System.out.println(imgFile.getOriginalFilename());

			try {	
				String url=parkService.uploadFile(imgFile,imgFile.getOriginalFilename());
				System.out.println(url);
				map.put("error", 0);
				map.put("url", ctxPath+"/busi/picture/"+url);
				map.put("message", "成功");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("error", 1);
				map.put("message", e);
			}
			return map;
		}
		
		@RequestMapping(value="/parkpicture/park/{floder}/{name}",produces="image/jpeg")
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
	}
