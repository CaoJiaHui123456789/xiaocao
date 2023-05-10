package com.cjxy.park.service.setUp;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjxy.park.bean.setUp.SysIndexSwiper;
import com.cjxy.park.bean.space.SysAptDiagram;
import com.cjxy.park.dao.base.MinioDao;
import com.cjxy.park.dao.setUp.IndexSwiperRepository;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;



@Service
public class IndexSwiperService {
	@Resource
	private  MinioDao minioDao;
	
	@Resource
	private IndexSwiperRepository indexSwiperRepository;
	
	public Integer saveIndexSwiper(SysIndexSwiper indexSwiper) {
		return indexSwiperRepository.save(indexSwiper).getIndexSwiperId();
	}
	
	
	public Integer uploadFile(MultipartFile multipartFile,SysIndexSwiper indexSwiper) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		String bucketName="park";
		String filePath="indexSwiper";
		String fileName=indexSwiper.getIndexSwiperName();
		fileName=fileName+".jpg";
		InputStream in = multipartFile.getInputStream();
		String url=minioDao.uploadFile(bucketName, filePath, fileName,in);
		indexSwiper.setIndexSwiperImgPath(url);
		return indexSwiperRepository.save(indexSwiper).getIndexSwiperId();
	}
	
	
	public long querySwiperNums() {
		return indexSwiperRepository.count();
	}
	
	public List<String> getPath() {
		return indexSwiperRepository.queryPath();
	}
	
	public String getPathById(Integer id) {
		return indexSwiperRepository.getPathById(id);
	}
	
	public List<Integer> getId() {
		return indexSwiperRepository.queryId();
	}
	
	
	public byte[] downloadFile(String bucketName, String filePath) {
		return minioDao.downloadFile(bucketName, filePath);
	}
	
	public void delById(int id) {
		indexSwiperRepository.deleteById(id);
	}
	
	public Boolean delImg(String fullPath) {
		return minioDao.deleteFile(fullPath);
	}
	
	
	
	
}
