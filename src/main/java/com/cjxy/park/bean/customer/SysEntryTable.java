package com.cjxy.park.bean.customer;

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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "companyName") })
public class SysEntryTable {
     private Integer entryId;    //入驻ID
     private String  companyName;  //企业名称
     private String entryLocation;  //入驻位置
     private String businessAct;   //经营活动
     private String investmentSource;  //投资来源
     private String industryClass;   //行业分类
     private String registerClass;   //登记注册
     private Boolean countryhighTech;  //国家级高级技术
     private Boolean listingSituation;  //上市情况
     private String  buildFactories;   //购地建厂
     private String  practioner;   //从业人员
     private String  teamComposition; //创业者团队构成
     private String incomeResearch; //收入级研发
     private String legalPerson;   //法人
     private String fixContact;  //固定联系人
     private Boolean approval;  //审批
     
     
    @Id
   	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public Integer getEntryId() {
		return entryId;
	}
	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}
	
	@Column(length = 50)
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(length = 50)
	public String getEntryLocation() {
		return entryLocation;
	}
	public void setEntryLocation(String entryLocation) {
		this.entryLocation = entryLocation;
	}
	
	@Column(length = 50)
	public String getBusinessAct() {
		return businessAct;
	}
	public void setBusinessAct(String businessAct) {
		this.businessAct = businessAct;
	}
	
	@Column(length = 50)
	public String getInvestmentSource() {
		return investmentSource;
	}
	public void setInvestmentSource(String investmentSource) {
		this.investmentSource = investmentSource;
	}
	
	@Column(length = 50)
	public String getIndustryClass() {
		return industryClass;
	}
	public void setIndustryClass(String industryClass) {
		this.industryClass = industryClass;
	}
	
	@Column(length = 50)
	public String getRegisterClass() {
		return registerClass;
	}
	public void setRegisterClass(String registerClass) {
		this.registerClass = registerClass;
	}
	
	@Column(length = 50)
	public Boolean getCountryhighTech() {
		return countryhighTech;
	}
	public void setCountryhighTech(Boolean countryhighTech) {
		this.countryhighTech = countryhighTech;
	}
	
	@Column(length = 50)
	public Boolean getListingSituation() {
		return listingSituation;
	}
	public void setListingSituation(Boolean listingSituation) {
		this.listingSituation = listingSituation;
	}
	
	@Column(length = 50)
	public String getBuildFactories() {
		return buildFactories;
	}
	public void setBuildFactories(String buildFactories) {
		this.buildFactories = buildFactories;
	}
	
	@Column(length = 50)
	public String getPractioner() {
		return practioner;
	}
	public void setPractioner(String practioner) {
		this.practioner = practioner;
	}
	
	@Column(length = 50)
	public String getTeamComposition() {
		return teamComposition;
	}
	public void setTeamComposition(String teamComposition) {
		this.teamComposition = teamComposition;
	}
	
	@Column(length = 50)
	public String getIncomeResearch() {
		return incomeResearch;
	}
	public void setIncomeResearch(String incomeResearch) {
		this.incomeResearch = incomeResearch;
	}
	
	@Column(length = 50)
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	@Column(length = 50)
	public String getFixContact() {
		return fixContact;
	}
	public void setFixContact(String fixContact) {
		this.fixContact = fixContact;
	}
	
	@Column(length = 50)
	public Boolean getApproval() {
		return approval;
	}
	public void setApproval(Boolean approval) {
		this.approval = approval;
	}
     
     
     
}
