package com.cjxy.park.service.setUp;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjxy.park.bean.setUp.SysNewsCont;
import com.cjxy.park.bean.space.SysAptDiagram;
import com.cjxy.park.dao.base.MinioDao;
import com.cjxy.park.dao.setUp.NewsContDao;
import com.cjxy.park.dao.setUp.NewsContRepository;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;


@Service
public class NewsContService {
	
	@Resource
	private  NewsContRepository newsContRepository;
	@Resource
	private  NewsContDao newsContDao;
	@Resource
	private  MinioDao minioDao;
	
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<SysNewsCont> queryAll(){
		return newsContRepository.findAll();
	}
	
	public Optional<SysNewsCont> queryById(Integer newcontId){
		return newsContRepository.findById(newcontId);
	}
	
	public Page<SysNewsCont> queryNewsCont(SysNewsCont newsCont, int pageNo, int pageSize)throws Exception{
		return newsContDao.queryNewsCont(newsCont,pageNo,pageSize);
	}
	
	/**
	 * 保存一个员工
	 * @return
	 */
	public Integer saveSysNewsCont(SysNewsCont newsCont) {
		return newsContRepository.save(newsCont).getNewcontId();
	}
	
	
	public void updSysNewsCont(SysNewsCont newsCont) {
		newsContRepository.save(newsCont);
	}
	
	public void delNewsCont(Integer newcontId) {
		newsContRepository.deleteById(newcontId);
	}
	
	
	public String uploadFile(MultipartFile multipartFile,String filename) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		String bucketName="park";
		String filePath="news";
		String fileName=filename;
		InputStream in = multipartFile.getInputStream();
		String url=minioDao.uploadFile(bucketName, filePath, fileName,in);
		return url;
	}
	
	
	public byte[] downloadFile(String bucketName, String filePath) {
		return minioDao.downloadFile(bucketName, filePath);
	}
	
}
	


