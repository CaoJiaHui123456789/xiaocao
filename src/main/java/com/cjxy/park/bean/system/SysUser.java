package com.cjxy.park.bean.system;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity  
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"userId","userCode"})})
public class SysUser {
	
	
	private Integer userId;
	  private String userCode; //编号
	  private String userPass; //密码	  
	  private String userName; //姓名
	  private Boolean userSex; //性别
	  private String userPhone;//电话
	  private String poster; //职务
	  private Date birthday;//生日
	  private Date entryDay;//入职时间
	  private String address;//住址
	  
	  private SysDept dept;//外键
	  
	  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name="userCode",length=10,nullable=false)
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(length = 50)
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	@Column(length = 50)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	 @Column
	public Boolean getUserSex() {
		return userSex;
	}
	public void setUserSex(Boolean userSex) {
		this.userSex = userSex;
	}
	@Column(length = 50)
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	@Column(length = 50)
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd" )
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd" )
	public Date getEntryDay() {
		return entryDay;
	}
	public void setEntryDay(Date entryDay) {
		this.entryDay = entryDay;
	}
	@Column(length = 50)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@ManyToOne
	@JoinColumn(name = "deptId")
	public SysDept getDept() {
		return dept;
	}
	public void setDept(SysDept dept) {
		this.dept = dept;
	}
	 
	
	  @Override
		public String toString() {
			return "SysUser [userId=" + userId + ", userCode=" + userCode + ", userPass=" + userPass + ", userName="
					+ userName + ", userSex=" + userSex + ", userPhone=" + userPhone + ", poster=" + poster + ", birthday="
					+ birthday + ", entryDay=" + entryDay + ", address=" + address + ", dept=" + dept + "]";
		}
}

