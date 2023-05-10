package com.cjxy.park.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {
	@Value("${minio.miniUser}")
	private String miniUser;
	@Value("${minio.miniPass}")
	private String miniPass;
	@Value("${minio.endpoint}")
	private String endpoint;

	/**
	 * 内网上传用的
	 * @return
	 */
	@Bean(name = "minioAccessClient")
	public MinioClient getMinioAccessClient() {
		MinioClient minioClient = MinioClient.builder()
				.endpoint(endpoint)
				.credentials(miniUser, miniPass).build();
		return minioClient;
	}
}
