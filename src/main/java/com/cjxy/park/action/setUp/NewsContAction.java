package com.cjxy.park.action.setUp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjxy.park.bean.setUp.SysIndexSwiper;
import com.cjxy.park.bean.setUp.SysNewsCont;
import com.cjxy.park.bean.setUp.SysNewsTable;
import com.cjxy.park.service.setUp.NewsContService;



@RestController
@RequestMapping(value="/newsCont")

public class NewsContAction {
	
	@Value(value="${server.servlet.context-path}")
	private String ctxPath;
	
	@Resource
	private NewsContService newsContService;
	
	
	/**
	 * 查询所有
	 * @return
	 */
	@RequestMapping(value = "/queryAllNewsCont")
	public Map<String,Object> queryAllNewsCont(){
		Map<String,Object> map=new HashMap<>();
		try {
			List<SysNewsCont> ls=newsContService.queryAll();
			map.put("rows", ls);
			map.put("total", ls.size());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
	}
	@RequestMapping(value = "/queryById")
	public Map<String,Object> queryById(int newcontentId){
		Map<String,Object> map=new HashMap<>();
		try {
			Optional<SysNewsCont> ls=newsContService.queryById(newcontentId);
			map.put("rows", ls);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
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
	
	
	
	
	
	
	/**
	 * 增加新闻
	 * @return
	 */
	
	@RequestMapping(value = "/addNewsCont")
	public Map<String,Object> addStaff(SysNewsCont newsCont,SysNewsTable newsTable,HttpServletRequest request,MultipartFile file){
		Map<String,Object> map=new HashMap<>();
		try {	
			Object userCode = request.getSession().getAttribute("userCode");
			String author=(String) userCode;                    //从session中获取作者
			
			Date date=new Date(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //格式化时间
			String formatDate=sdf.format(date);
			newsCont.setDateDay(formatDate);
		
			newsCont.setNewsTable(newsTable);
			newsCont.setAuthor(author);
			String fileName =UUID.randomUUID().toString()+newsCont.getNewsName()+".jpg";
			String url =newsContService.uploadFile(file, fileName);  //使用UUID防止图片覆盖
			newsCont.setPicture(url);
			int newcontid=newsContService.saveSysNewsCont(newsCont);
			if (newcontid>0) {
				map.put("mess", "保存成功！");
			}else {
				map.put("mess", "保存失败！");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}
	/**
	 * 
	 * @param修改
	 * @return
	 */
	
	@RequestMapping(value = "/updNewsCont")
	public Map<String,Object> updStaff(SysNewsCont newsCont,SysNewsTable newsTable,HttpServletRequest request,MultipartFile file){
		Map<String,Object> map=new HashMap<>();
		try {
			Object userCode = request.getSession().getAttribute("userCode");
			String author=(String) userCode;                    //从session中获取作者
			
			Date date=new Date(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //格式化时间
			String formatDate=sdf.format(date);
			newsCont.setDateDay(formatDate);
		
			newsCont.setNewsTable(newsTable);
			newsCont.setAuthor(author);
			String fileName =UUID.randomUUID().toString()+newsCont.getNewsName()+".jpg";
			String url =newsContService.uploadFile(file, fileName);  //使用UUID防止图片覆盖
			newsCont.setPicture(url);
			int newcontid=newsContService.saveSysNewsCont(newsCont);
			if (newcontid>0) {
				map.put("mess", "保存成功！");
			}else {
				map.put("mess", "保存失败！");
			}
			map.put("mess", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}
	
	/**
	 * 
	 * @param 删除
	 * @return
	 */
	@RequestMapping(value = "/delNewsCont")
	public Map<String,Object> delNewsCont(Integer newcontId){
		Map<String,Object> map=new HashMap<>();
		try {
			newsContService.delNewsCont(newcontId);
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
			String url=newsContService.uploadFile(imgFile,imgFile.getOriginalFilename());
			map.put("error", 0);
			map.put("url", ctxPath+"/busi/picture/"+url);
			System.out.println(map.get("url"));

			
			map.put("message", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", e);
		}
		return map;
	}
	
	
	@RequestMapping(value="/picture/park/{floder}/{name}",produces="image/jpeg")
	public byte[] getPicture(HttpServletRequest request,@PathVariable("floder") String floder,@PathVariable("name") String name){
		String requestURL = request.getRequestURL().toString();
		String path=floder+"/"+name;
		byte[] data;
		try {
			data=newsContService.downloadFile("park", path);
		}catch(Exception e){
			System.out.println(e);
			data=null;
		}
		return data;
		
	}
}


