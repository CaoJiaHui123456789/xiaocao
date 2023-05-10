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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "houseCode") })
public class SysHouse {

	private Integer houseId; // 房号
	private String houseCode; // 房间编号
	private String houseStatus; // 房间状态
	private String houseType; // 房间类型
	private Double housePrice; // 房价
	private Boolean payStatus; // 交付状态
	private Double areaRent; // 租赁面积
	private Double basePrice; // 基准单价
	private String payStandard; // 交付标准
	private Double tollMoney; // 月收金额
	private Double aircondPrice; // 空调单价
	private String aircondTollmode; // 空调收费模式
	private String custom; // 客户

	private SysBuilding building; // 楼宇
	private SysAptDiagram aptdiagram;  //户型图

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "houseId", length = 10, nullable = false)
	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	@Column(length = 100)
	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	@Column(length = 100)
	public String getHouseStatus() {
		return houseStatus;
	}

	public void setHouseStatus(String houseStatus) {
		this.houseStatus = houseStatus;
	}

	@Column(length = 100)
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	@Column(length = 50)
	public Double getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(Double housePrice) {
		this.housePrice = housePrice;
	}

	@Column(length = 50)
	public Boolean getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Boolean payStatus) {
		this.payStatus = payStatus;
	}

	@Column(length = 50)
	public Double getAreaRent() {
		return areaRent;
	}

	public void setAreaRent(Double areaRent) {
		this.areaRent = areaRent;
	}

	@Column(length = 50)
	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	@Column(length = 255)
	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	@Column(length = 100)
	public String getPayStandard() {
		return payStandard;
	}

	public void setPayStandard(String payStandard) {
		this.payStandard = payStandard;
	}

	@Column(length = 100)
	public Double getTollMoney() {
		return tollMoney;
	}

	public void setTollMoney(Double tollMoney) {
		this.tollMoney = tollMoney;
	}

	@Column(length = 100)
	public Double getAircondPrice() {
		return aircondPrice;
	}

	public void setAircondPrice(Double aircondPrice) {
		this.aircondPrice = aircondPrice;
	}

	@Column(length = 100)
	public String getAircondTollmode() {
		return aircondTollmode;
	}

	public void setAircondTollmode(String aircondTollmode) {
		this.aircondTollmode = aircondTollmode;
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
	@JoinColumn(name = "adId")
	public SysAptDiagram getAptdiagram() {
		return aptdiagram;
	}

	public void setAptdiagram(SysAptDiagram aptdiagram) {
		this.aptdiagram = aptdiagram;
	}

	
}
