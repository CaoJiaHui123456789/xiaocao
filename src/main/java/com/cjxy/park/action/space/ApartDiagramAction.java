package com.cjxy.park.action.space;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cjxy.park.bean.space.SysAptDiagram;
import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysPark;
import com.cjxy.park.dao.base.MinioDao;
import com.cjxy.park.service.space.AptDiagramService;

import io.minio.MinioAsyncClient;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@RestController
@RequestMapping(value="/aptDiagram")
public class ApartDiagramAction {
	@Resource
	AptDiagramService adService;
	@Resource
	private  MinioDao minioDao;
	
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryAllAd")
	public List<SysAptDiagram> queryAllAd() {
		try {
			List<SysAptDiagram> ls = adService.queryAll();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	@RequestMapping(value = "/queryAptDiagram")
	public Map<String,Object> queryAptDiagram(SysAptDiagram  Ad,int pageNo,int pageSize ){
		Map<String,Object> map=new HashMap<>();
		try {
			Page<SysAptDiagram> pg=adService.queryAd(Ad, pageNo, pageSize);
			map.put("rows", pg.getContent());
			map.put("total",pg.getTotalElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", null);
			map.put("total", 0);
		}
		return map;
	}
	
	@RequestMapping(value = "/addDiagram", method = {  RequestMethod.POST })
    public Map<String,Object> addDiagram(MultipartFile file,SysAptDiagram aptDiagram,SysBuilding building,SysPark park) throws Exception, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException, Exception{
		Map<String, Object> message = new HashMap<String, Object>();
		try {
			
			aptDiagram.setPark(park);
			aptDiagram.setBuilding(building);
			adService.uploadFile(file,aptDiagram);
		} catch (Exception e) {
			
			//
//			minioDao.deleteFile( )
			
		}
		
		return message;
    }
	
	@RequestMapping(value = "/addAd")
	public Map<String,Object> addUser(SysAptDiagram ad){
		Map<String,Object> map=new HashMap<>();
		try {
			int userid=adService.saveAd(ad);
			ad.toString();
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
	
	@RequestMapping(value = "/delSysAd")
	public Map<String,Object> delSysPark(Integer adId){
		Map<String,Object> map=new HashMap<>();
		try {
			adService.delSysPark(adId);
			map.put("mess", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mess", e.toString());
		}
		return map;
	}
	
	
	
}
