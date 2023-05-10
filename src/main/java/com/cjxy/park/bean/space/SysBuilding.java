package com.cjxy.park.bean.space;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Proxy(lazy = false)
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "buildingName") })
public class SysBuilding {
	private Integer buildingId; // 楼宇编号自增
	private String buildingName; // 楼宇名称
	private Integer floorNumber; // 楼宇层数
	private Integer houseNumber; // 房源数量
	private Double buildingArea; // 建筑面积
	private Double publicArea; // 公共面积
	private Double contractArea; // 合约面积
	private Double avgArea; // 户均面积
	private Integer cusNumber; // 客户数量
	private Double rentArea; // 租赁面积
	private SysPark park; // 园区

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buildingId", length = 10, nullable = false)
	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	@Column(length = 50)
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	@Column(length = 50)
	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	@Column(length = 100)
	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Column(length = 50)
	public Double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Double buildingArea) {
		this.buildingArea = buildingArea;
	}

	@Column(length = 50)
	public Double getRentArea() {
		return rentArea;
	}

	public void setRentArea(Double rentArea) {
		this.rentArea = rentArea;
	}

	@Column(length = 50)
	public Integer getCusNumber() {
		return cusNumber;
	}

	public void setCusNumber(Integer cusNumber) {
		this.cusNumber = cusNumber;
	}

	@Column(length = 50)
	public Double getAvgArea() {
		return avgArea;
	}

	public void setAvgArea(Double avgArea) {
		this.avgArea = avgArea;
	}

	@Column(length = 50)
	public Double getPublicArea() {
		return publicArea;
	}

	public void setPublicArea(Double publicArea) {
		this.publicArea = publicArea;
	}

	@Column(length = 50)
	public Double getContractArea() {
		return contractArea;
	}

	public void setContractArea(Double contractArea) {
		this.contractArea = contractArea;
	}

	@ManyToOne
	@JoinColumn(name = "parkId")
	public SysPark getPark() {
		return park;
	}

	public void setPark(SysPark park) {
		this.park = park;
	}

}
