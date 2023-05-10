package com.cjxy.park.bean.system;

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

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Proxy(lazy = false)
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "deptCode") })
public class SysDept {
	 private Integer deptId;    //自增ID
     private String deptCode;   //部门编号
     private String deptName;   //部门名称
     private String deptLocation;  //部门位置
     private String deptTel;     //部门电话
     
     private List<SysUser> user;//所属该部门的员工列表
    @Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@Column(name = "deptCode",length = 50,nullable = false)
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	@Column(length = 50)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(length = 50)
	public String getDeptLocation() {
		return deptLocation;
	}
	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}
	@Column(length = 50)
	public String getDeptTel() {
		return deptTel;
	}
	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH,mappedBy = "dept")
	@JsonIgnore
	public List<SysUser> getUser() {
		return user;
	}
	public void setUser(List<SysUser> user) {
		this.user = user;
	} 
     
     
}
