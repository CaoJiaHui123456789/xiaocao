package com.cjxy.park.dao.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cjxy.park.util.Constant;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.PutObjectArgs.Builder;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketPolicyArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MinioDao {


	@Resource(name = "minioAccessClient")
	MinioClient minioClient;
	
	
	public MinioClient getMinioClient() {
		return minioClient;
	}

	public void setMinioClient(MinioClient minioClient) {
		this.minioClient = minioClient;
	}


	/**
	 * 上传通用文件
	 * @param bucketName
	 * @param filePath
	 * @param fileName
	 * @param fileInfo
	 * @param file
	 * @return
	 */
	public String uploadFile(String bucketName, String filePath, String fileName, String fileInfo,
			MultipartFile file) {
		try {
			if (null == fileName) {
				fileName = file.getOriginalFilename();
				String[] split = fileName.split("\\.");
				String uuid = UUID.randomUUID().toString();
				if (split.length > 1) {
					fileName = uuid + "." + split[1];
				} else {
					fileName = uuid;
				}
			}
			if (!file.isEmpty()) {
				InputStream in =  file.getInputStream();
				return uploadFile( bucketName,  filePath,  fileName,fileInfo,  in, file.getContentType());
			} else {
				throw new java.lang.RuntimeException("上传文件不能为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 上传文件-通用
	 * @param bucketName
	 * @param filePath
	 * @param fileName
	 * @param fileInfo
	 * @param file
	 * @return
	 */
	
	public String uploadFile(String bucketName, String filePath, String fileName, String fileInfo,
			InputStream in,String contentType)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException,
			IllegalArgumentException, IOException {
		if (null==fileName||"".equals(fileName)) {
			throw new java.lang.RuntimeException("文件名不能为null");
		}
		createBucket(bucketName);
		Builder bd = PutObjectArgs.builder();
		bd.bucket(bucketName)
			.object(filePath + "/" + fileName)
			.stream(in, in.available(), -1);
		Map<String, String> tag = new HashMap<>();
		if (null != fileInfo) {
			tag.put("fileInfo", fileInfo);
			bd.tags(tag);
		}
		if (null != contentType) {
			bd.contentType(contentType);
		}
		minioClient.putObject(bd.build());
		log.debug("上传文件至：{}", bucketName + "/" + filePath + "/" + fileName);
		return bucketName + "/" + filePath + "/" + fileName; 
	}
	
	public String uploadFile(String bucketName, String filePath, String fileName,
			InputStream in,String contentType)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException,
			IllegalArgumentException, IOException {
		return uploadFile( bucketName,  filePath,  fileName, null,
				 in, contentType);
	}
	/**
	 * 
	 * @param bucketName
	 * @param filePath
	 * @param fileName
	 * @param in
	 * @return
	 * @throws InvalidKeyException
	 * @throws ErrorResponseException
	 * @throws InsufficientDataException
	 * @throws InternalException
	 * @throws InvalidResponseException
	 * @throws NoSuchAlgorithmException
	 * @throws ServerException
	 * @throws XmlParserException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public String  uploadFile(String bucketName, String filePath, String fileName,
			InputStream in)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException,
			IllegalArgumentException, IOException {
		return uploadFile( bucketName,  filePath,  fileName, null,
				 in, null);
	}
	
	public String uploadFile(String bucketName, String filePath, String fileName,String fileInfo,
			byte[] buffer)
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException,
			IllegalArgumentException, IOException {
		InputStream in=new ByteArrayInputStream(buffer);
		return uploadFile( bucketName,  filePath,  fileName, fileInfo,
				 in, null);
	}

	/**
	 * 创建顶层文件夹
	 * 
	 * @param bucketName
	 * @throws InvalidKeyException
	 * @throws ErrorResponseException
	 * @throws InsufficientDataException
	 * @throws InternalException
	 * @throws InvalidResponseException
	 * @throws NoSuchAlgorithmException
	 * @throws ServerException
	 * @throws XmlParserException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public void createBucket(String bucketName) throws InvalidKeyException, ErrorResponseException,
			InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException,
			ServerException, XmlParserException, IllegalArgumentException, IOException {
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
		if (!found) {
			log.debug("创建顶层文件夹：{}", bucketName);
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
			try {
//				setBucketPolicy(bucketName);
			} catch (Exception e) {
				//log.error("访问策略设置失败，请手动设置");
			}
		}

	}

	/**
	 * 删除文件
	 * 
	 * @param bucketName
	 * @param filePath
	 * @param fileName
	 */

	public boolean deleteFile(String bucketName, String filePath) {
		try {
			minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName)
					.object(filePath).build());
			log.debug("删除文件：{}", bucketName + "/" +filePath);
			return true;
		} catch (Exception e) {
			if (e.toString().indexOf("The specified key does not exist")>-1) {
				log.debug("文件不存在：{}", bucketName + "/" + filePath);
			}else {
				log.debug("删除文件失败：{}", bucketName + "/" + filePath);
				e.printStackTrace();
			}
			return false;
		}
	}
	
	public boolean deleteFile(String fullPath) {
		try {
			String bucketName=Constant.sysName;
			String filePath=fullPath.substring(Constant.sysName.length()+1);
			return deleteFile(bucketName,filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * 获取对象信息
	 * @param bucketName
	 * @param objectName
	 * @return
	 * @throws Exception
	 */
	public boolean  isExist(String bucketName, String objectName) throws Exception {
		try {
			StatObjectResponse  resp=minioClient.statObject(StatObjectArgs.builder()
					.bucket(bucketName).object(objectName).build());
			if(null!=resp) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
    }


	/**
	 * 
	 * @param bucketName
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public byte[] downloadFile(String bucketName, String filePath) {
		try (InputStream stream = minioClient.getObject(
				GetObjectArgs.builder().bucket(bucketName).object(filePath).build())) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int n = 0;
			while (-1 != (n = stream.read(buffer))) {
				output.write(buffer, 0, n);
			}
			log.debug("下载文件成功：{}", bucketName + "/" + filePath);
			return output.toByteArray();
		} catch (Exception e) {
			if (e.toString().indexOf("does not exist")>-1) {
				log.debug("文件不存在：{}", bucketName + "/" + filePath);
			}else {
				log.debug("下载文件失败：{}", bucketName + "/" + filePath);
				e.printStackTrace();
			}
			return null;
		}
	}
	/**
	 * 
	 * @param fullPath
	 * @return
	 */
	public byte[] downloadFile(String fullPath) {
		String bucketName=Constant.sysName;
		String filePath=fullPath.substring(Constant.sysName.length()+1);
		return downloadFile(bucketName,  filePath);
	}

	/**
	 * 获取预览完整链接
	 * @param bucketName
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public String getPreviewUrl(String bucketName, String filePath, String fileName) {
		try {
			Map<String, String> reqParams = new HashMap<String, String>();
			reqParams.put("response-content-type", "image/jpeg");

			String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET)
					.bucket(bucketName).object(filePath + "/" + fileName).expiry(2, TimeUnit.HOURS)
					.extraQueryParams(reqParams).build());
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public void setBucketPolicy(String bucketName) throws InvalidKeyException, ErrorResponseException,
			InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException,
			ServerException, XmlParserException, IllegalArgumentException, IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("{\r\n"
				+ "    \"Version\": \"2012-10-17\",\r\n"
				+ "    \"Statement\": [\r\n"
				+ "        {\r\n"
				+ "            \"Effect\": \"Allow\",\r\n"
				+ "            \"Principal\": {\r\n"
				+ "                \"AWS\": [\r\n"
				+ "                    \"*\"\r\n"
				+ "                ]\r\n"
				+ "            },\r\n"
				+ "            \"Action\": [\r\n"
				+ "                \"s3:GetBucketLocation\",\r\n"
				+ "                \"s3:ListBucket\"\r\n"
				+ "            ],\r\n"
				+ "            \"Resource\": [\r\n"
				+ "                \"arn:aws:s3:::"+bucketName+"\"\r\n"
				+ "            ]\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"Effect\": \"Allow\",\r\n"
				+ "            \"Principal\": {\r\n"
				+ "                \"AWS\": [\r\n"
				+ "                    \"*\"\r\n"
				+ "                ]\r\n"
				+ "            },\r\n"
				+ "            \"Action\": [\r\n"
				+ "                \"s3:GetObject\"\r\n"
				+ "            ],\r\n"
				+ "            \"Resource\": [\r\n"
				+ "                \"arn:aws:s3:::"+bucketName+"/*\"\r\n"
				+ "            ]\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}");
		minioClient
				.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(builder.toString()).build());
	}
	
	public static void main(String[] args) {
		try {
			String endpint = "http://192.168.0.193:9000";
			String miniUser = "admin";
			String miniPass = "minio1982";
			MinioClient minioClient = MinioClient.builder().endpoint(endpint).credentials(miniUser, miniPass).build();
			MinioDao minio = new MinioDao();
			minio.setMinioClient(minioClient);

//			minio.setBucketPolicy("vhbc");
//			File file = new File("D:\\test.png");
//			FileInputStream in = new FileInputStream(file);
//			minio.uploadFile("vhbc", String.PRODUCT,"bb.png",in);
//			minio.deleteFile("vhbc", String.PRODUCT,"test.png");
//			String url = minio.getPreviewUrl("vhbc", String.PRODUCT, "bb.png");
//			System.out.println(url);
			// System.out.println(is.available());
//			System.out.println("over");
//			String aa="train/aa/dd.jpg";
//			System.out.println(aa.substring(Constant.sysName.length()));
			System.out.println(minio.isExist("auth", "sign/2019190026.sign"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
