package com.cjxy.park.bean.space;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Proxy(lazy = false)
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="parkId")})
public class SysPark {
	private Integer parkId; 
	private String parkName;//名称
	private String parkPro; //省份
	private String parkCity;//市
	private String parkDis;	//区
	private String parkAddress;//详细地址
	private String parkAdmin;//管理员
	private String parkArea;//面积
	private String parkIntro; //园区简介
	private String parkPhone;  //园区电话
	private String parkMailbox; //园区邮箱
	private String parkSiteurl; //园区网址
	private List<SysBuilding> building;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name = "parkId",length = 10,nullable = false)
	public Integer getParkId() {
		return parkId;
	}
	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}
	@Column(length = 100)
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	@Column(length = 100)
	public String getParkPro() {
		return parkPro;
	}
	public void setParkPro(String parkPro) {
		this.parkPro = parkPro;
	}
	@Column(length =100)
	public String getParkCity() {
		return parkCity;
	}
	public void setParkCity(String parkCity) {
		this.parkCity = parkCity;
	}
	@Column(length = 100)
	public String getParkDis() {
		return parkDis;
	}
	public void setParkDis(String parkDis) {
		this.parkDis = parkDis;
	}
	@Column(length = 100)
	public String getParkAddress() {
		return parkAddress;
	}
	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
	}
	@Column(length = 100)
	public String getParkAdmin() {
		return parkAdmin;
	}
	public void setParkAdmin(String parkAdmin) {
		this.parkAdmin = parkAdmin;
	}
	@Column(length = 100)
	public String getParkArea() {
		return parkArea;
	}
	public void setParkArea(String parkArea) {
		this.parkArea = parkArea;
	}
	
	@Column(columnDefinition = "text")
	public String getParkIntro() {
		return parkIntro;
	}
	public void setParkIntro(String parkIntro) {
		this.parkIntro = parkIntro;
	}
	
	@Column(length = 100)
	public String getParkPhone() {
		return parkPhone;
	}
	public void setParkPhone(String parkPhone) {
		this.parkPhone = parkPhone;
	}
	
	@Column(length = 100)
	public String getParkMailbox() {
		return parkMailbox;
	}
	public void setParkMailbox(String parkMailbox) {
		this.parkMailbox = parkMailbox;
	}
	
	@Column(length = 100)
	public String getParkSiteurl() {
		return parkSiteurl;
	}
	public void setParkSiteurl(String parkSiteurl) {
		this.parkSiteurl = parkSiteurl;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "park")
    @JsonIgnore
    public List<SysBuilding> getBuilding() {
        return building;
    }

    public void setBuilding(List<SysBuilding> building) {
        this.building = building;
    }
	
    
}
