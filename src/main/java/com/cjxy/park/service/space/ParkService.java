package com.cjxy.park.service.space;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysPark;
import com.cjxy.park.dao.base.MinioDao;
import com.cjxy.park.dao.space.ParkDao;
import com.cjxy.park.dao.space.ParkRepository;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Service
public class ParkService {

	@Resource
	private ParkRepository parkRepository;
	@Resource
	private ParkDao parkDao;
	@Resource
	private MinioDao minioDao;

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<SysPark> queryAll() {
		return parkRepository.findAll();
	}

	public Page<SysPark> queryPark(SysPark park, int pageNo, int pageSize) throws Exception {
		return parkDao.queryPark(park, pageNo, pageSize);
	}

	// 保存
	public String saveSysPark(SysPark park) {
		if (null == park.getParkName() || "".equals(park.getParkName())) {
			return "园区名称不能为空";
		} else {
			List<SysPark> ls = parkRepository.findByParkName(park.getParkName());
			if (null != ls && ls.size() > 0) {
				return park.getParkName() + "已经存在";
			}
		}
		if (null == park.getParkPro()) {
			return "所属省份不能为空";
		}
		if (null == park.getParkCity()) {
			return "所属市名不能为空";
		}
		if (null == park.getParkDis()) {
			return "所属区名不能为空";
		}
		if (null == park.getParkAddress()) {
			return "详细地址不能为空";
		}
		if (null == park.getParkAdmin()) {
			return "管理人员不能为空";
		}
		if (null == park.getParkArea()) {
			return "园区面积不能为空";
		}
		if (null == park.getParkIntro()) {
			return "园区简介不能为空";
		}
		if (null == park.getParkPhone()) {
			return "园区电话不能为空";
		}
		if (null == park.getParkMailbox()) {
			return "园区邮箱不能为空";
		}
		if (null == park.getParkSiteurl()) {
			return "园区网址不能为空";
		}

		parkRepository.save(park);
		return "success";
	}
//		return parkRepository.save(park).getParkId();
//	}

	// 修改
	public String updSysPark(SysPark park) {
		String parkName = park.getParkName();
		List<SysPark> lsList = parkRepository.findByParkName(parkName);
		if (null == park.getParkName() || "".equals(park.getParkName())) {
			return "园区名称不能为空";
		} else if (lsList != null && lsList.size() > 0) {
			SysPark sPark = lsList.get(0);
			if (null == park.getParkPro()) {
				return "所在省份不能为空";
			}
			if (null == park.getParkCity()) {
				return "所在市名不能为空";
			}
			if (null == park.getParkDis()) {
				return "所在区名不能为空";
			}
			if (null == park.getParkAddress()) {
				return "详细地址不能为空";
			}
			if (null == park.getParkAdmin()) {
				return "管理人员不能为空";
			}
			if (null == park.getParkArea()) {
				return "园区面积不能为空";
			}
			if (null == park.getParkIntro()) {
				return "园区简介不能为空";
			}
			if (null == park.getParkPhone()) {
				return "园区电话不能为空";
			}
			if (null == park.getParkMailbox()) {
				return "园区邮箱不能为空";
			}
			if (null == park.getParkSiteurl()) {
				return "园区网址不能为空";
			}
			parkRepository.save(park);
		} else {
			return parkName + "已经存在";
		}
	   return"success";
	}

	// 删除
	public void delSysPark(Integer parkId) {
	
		parkRepository.deleteById(parkId);
	}

	public String uploadFile(MultipartFile multipartFile,String filename) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		String bucketName="park";
		String filePath="parkintro";
		String fileName=filename;
		InputStream in = multipartFile.getInputStream();
		String url=minioDao.uploadFile(bucketName, filePath, fileName,in);
		return url;
	}

	public byte[] downloadFile(String bucketName, String filePath) {
		return minioDao.downloadFile(bucketName, filePath);
	}

	public List<SysPark> getparkingByName(String parkName){
		return parkRepository.findByParkName(parkName);
	}
}
