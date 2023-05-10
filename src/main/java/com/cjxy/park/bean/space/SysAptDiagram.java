package com.cjxy.park.bean.space;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;


@Proxy(lazy = false)
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="id")})
public class SysAptDiagram {
	private Integer adId;
	private String adCode; //户型代码
	private String img;    
	private String pattern;//格局 （几室几厅几卫）
	private String toward;//朝向
	private double area;
	private SysBuilding building; 
	private SysPark park;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id",length = 10,nullable = false)
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@Column(length = 50)
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Column(length = 10)
	public String getToward() {
		return toward;
	}
	public void setToward(String toward) {
		this.toward = toward;
	}
	
	@Column(length = 50)
	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	
	@ManyToOne
	@JoinColumn(name = "buildingId")
	public SysBuilding getBuilding() {
		return building;
	}
	public void setBuilding(SysBuilding building) {
		this.building = building;
	}
	
	@ManyToOne
	@JoinColumn(name = "parkId")
	public SysPark getPark() {
		return park;
	}
	public void setPark(SysPark park) {
		this.park = park;
	}
	@Column(length = 10)
	public String getAdCode() {
		return adCode;
	}
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
	@Column(length = 200)
	public String getImg() {
		return img;
	}
	
}
