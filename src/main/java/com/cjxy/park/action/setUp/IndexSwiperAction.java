package com.cjxy.park.action.setUp;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.imageio.ImageIO;


import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjxy.park.bean.setUp.SysIndexSwiper;
import com.cjxy.park.service.setUp.IndexSwiperService;

import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@RestController
@RequestMapping(value="/indexSwiper")
public class IndexSwiperAction {
	
	@Resource
	IndexSwiperService indexSwiperService;
	
	
	@RequestMapping(value = "/addIndexSwiper", method = {  RequestMethod.POST })
    public Map<String,Object> addIndexSwiper(MultipartFile file,SysIndexSwiper indexSwiper) throws Exception, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException, Exception{
		Map<String, Object> message = new HashMap<String, Object>();
		MultipartFile mfile2=file;
		long count=indexSwiperService.querySwiperNums();
		if(count<5) {
			File tempfile=MultipartFileToFile(file);
			if(isImage(tempfile)) {
				Integer id=indexSwiperService.uploadFile(mfile2, indexSwiper);
				if(id>=0) {
					message.put("mess","保存成功");
				}else {
					message.put("mess","保存失败");
				}
				
			}else {
				message.put("mess","图片格式有误！");	
			}
			
		}else {
			message.put("mess", "轮播图片已达上限");
		}
		return message;
    }
	
	
		//加载图片
//		@RequestMapping(value="/iniImg",produces="image/jpeg")
//		public Map<String,byte[]> getPicture1(){
//			Map<String,byte[]> map= new HashMap<String,byte[]>();
//			
//			String path ="";
//			byte[] data;
//				List<String> list=indexSwiperService.getPath();
//				for(int i=0;i<=4;i++) {
//					path=list.get(i);
//					data=indexSwiperService.downloadFile("park", "indexSwiper"+path);
//					map.put("a"+i,data);
//				}
//			return map;
//		}
	
	
	@RequestMapping(value="/iniImg",produces="image/jpeg")
	public String iniImg(int index){
		String path ="";
		Integer id;
		byte[] data;
		List<String> list=indexSwiperService.getPath();
		List<Integer> listId=indexSwiperService.getId();
		path=list.get(index);
		id=listId.get(index);
		path=path.split("/")[1]+"/"+path.split("/")[2];
		data=indexSwiperService.downloadFile("park",path);
		String base="";
		try {
			 base=getImageString(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		base="data:image/jpg;base64,"+base;	
		base=id+"!"+base;
		
		System.out.println(base);
		return base;
	}
	
	@RequestMapping(value="/getImgList")
	public List<String> getPath(){
		return indexSwiperService.getPath();
	}
	@RequestMapping(value="/picture/park/{floder}/{name}",produces="image/jpeg")
	public byte[] getPicture(HttpServletRequest request,@PathVariable("floder") String floder,@PathVariable("name") String name){
		String requestURL = request.getRequestURL().toString();
		String path=floder+"/"+name;
		byte[] data;
		try {
			data=indexSwiperService.downloadFile("park", path);
		}catch(Exception e){
			System.out.println(e);
			data=null;
		}
		return data;
		
	}
	
	@RequestMapping(value = "/delImg", method = {  RequestMethod.POST })
	public Map<String,String> delImg(int[] typeIDArray) {
		Map<String,String> map=new HashMap<String, String>();
		for (int i : typeIDArray) {
			indexSwiperService.delById(i);
			Boolean flag= indexSwiperService.delImg(indexSwiperService.getPathById(i));
			if(flag==true) {
				map.put("mess","删除成功！");
			}else {
				map.put("mess","删除失败！");
			}
			
		}
		return map;
	}
	
	
	
	
	
	//二进制流转Base64
	public static String getImageString(byte[] data) throws IOException {
		String encode = Base64.getEncoder().encodeToString(data);
        return encode;
    }
	
	
	/**
     * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
     * @param imageFile
     * @return
     */
    public static boolean isImage(File imageFile) {
        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }
    
    
    
    /**
     * 将MultipartFile转换为File
     * @param multiFile
     * @return
     */
    
    public static File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若须要防止生成的临时文件重复,能够在文件名后添加随机码
        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
