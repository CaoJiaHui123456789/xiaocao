package com.cjxy.park.bean.setUp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

@Proxy(lazy = false)
@Entity
public class SysIndexSwiper {
	
	
	private Integer indexSwiperId;    //轮播图id
	private String indexSwiperImgPath;//轮播图存储路径
	private String indexSwiperName;   //轮播图名称
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "indexSwiperId", length = 10, nullable = false)
	public Integer getIndexSwiperId() {
		return indexSwiperId;
	}
	public void setIndexSwiperId(Integer indexSwiperId) {
		this.indexSwiperId = indexSwiperId;
	}
	
	@Column(length = 100)
	public String getIndexSwiperImgPath() {
		return indexSwiperImgPath;
	}
	public void setIndexSwiperImgPath(String indexSwiperImgPath) {
		this.indexSwiperImgPath = indexSwiperImgPath;
	}
	
	@Column(length = 100)
	public String getIndexSwiperName() {
		return indexSwiperName;
	}
	public void setIndexSwiperName(String indexSwiperName) {
		this.indexSwiperName = indexSwiperName;
	}
	
	

}
