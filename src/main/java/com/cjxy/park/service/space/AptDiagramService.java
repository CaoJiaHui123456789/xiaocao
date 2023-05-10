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
import com.cjxy.park.bean.space.SysAptDiagram;
import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysHouse;
import com.cjxy.park.dao.base.MinioDao;
import com.cjxy.park.dao.space.AptDiagramDao;
import com.cjxy.park.dao.space.AptDiagramRepository;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Service
public class AptDiagramService {
	
	@Resource
	private  AptDiagramDao adDao;
	@Resource
	private  MinioDao minioDao;
	@Resource
	private AptDiagramRepository adRepository;
	//查询所有
	public List<SysAptDiagram> queryAll() {
		return adRepository.findAll();
	}

	
	public Page<SysAptDiagram> queryAd(SysAptDiagram  Ad, int pageNo, int pageSize)throws Exception{
		return adDao.queryAd(Ad,pageNo,pageSize);
	}
	
	public void downloadFile(String bucketName, String filePath) {
		minioDao.downloadFile(bucketName, filePath);
	}
	
	
	public Integer uploadFile(MultipartFile multipartFile,SysAptDiagram aptDiagram) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		String bucketName="park";
		String filePath="aptDiagram";
		String fileName=aptDiagram.getAdCode();
		fileName=fileName+".jpg";
		InputStream in = multipartFile.getInputStream();
		String url=minioDao.uploadFile(bucketName, filePath, fileName,in);
		aptDiagram.setImg(url);
		Integer id=adRepository.save(aptDiagram).getAdId();
		return id;
	}
	public Integer saveAd(SysAptDiagram ad) {
		return adRepository.save(ad).getAdId();
	}
	
	public void delSysPark(Integer adId) {
		adRepository.deleteById(adId);
	}


	
	
}
