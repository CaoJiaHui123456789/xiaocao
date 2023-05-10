package com.cjxy.park.bean.setUp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class SysNewsCont {
	private Integer newcontId;  //自增编号
	private String newsName;    //标题 
	private String subtitleName;//副标题
	private String source;		//来源
	private String dateDay;		//发布时间
	private String author;		//作者
	private String content;		//内容
	private String picture;		//图片
	private Boolean recommend;  //是否推荐
	private SysNewsTable newsTable; //模块（外键）
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "newcontId", length = 10, nullable = false)
	public Integer getNewcontId() {
		return newcontId;
	}
	public void setNewcontId(Integer newcontId) {
		this.newcontId = newcontId;
	}

	@Column(length = 50)
	public String getSubtitleName() {
		return subtitleName;
	}
	public void setSubtitleName(String subtitleName) {
		this.subtitleName = subtitleName;
	}
	
	@Column
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
	public String getDateDay() {
		return dateDay;
	}
	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}

	@Column(length = 50)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	
	@Column(columnDefinition = "text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Column
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(length = 50)
	public String getNewsName() {
		return newsName;
	}
	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}
	
	@ManyToOne
	@JoinColumn(name = "newsId" )
	public SysNewsTable getNewsTable() {
		return newsTable;
	}
	public void setNewsTable(SysNewsTable newsTable) {
		this.newsTable = newsTable;
	}
	
	@Column
	public Boolean getRecommend() {
		return recommend;
	}
	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}
	

}
