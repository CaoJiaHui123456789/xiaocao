package com.cjxy.park.bean.setUp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Proxy;

	@Proxy(lazy = false)
	@Entity
	@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "newsCode") })
	public class SysNewsTable {
		private Integer newsId; // 新闻编号自增
		private String newsCode; // 新闻编号
		private String newsHead; // 新闻模块
		private Integer newsRank;//新闻等级
		private String newsSum;   //新闻概述
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "newsId", length = 10, nullable = false)
		public Integer getNewsId() {
			return newsId;
		}

		public void setNewsId(Integer newsId) {
			this.newsId = newsId;
		}

		@Column(length = 50)
		public String getNewsCode() {
			return newsCode;
		}

		public void setNewsCode(String newsCode) {
			this.newsCode = newsCode;
		}

		@Column(length = 50)
		public String getnewsHead() {
			return newsHead;
		}

		public void setNewsHead(String newsHead) {
			this.newsHead = newsHead;
		}
		@Column(length = 50)
		public Integer getNewsRank() {
			return newsRank;
		}

		public void setNewsRank(Integer newsRank) {
			this.newsRank = newsRank;
		}

		public String getNewsSum() {
			return newsSum;
		}

		public void setNewsSum(String newsSum) {
			this.newsSum = newsSum;
		}
		
	}
