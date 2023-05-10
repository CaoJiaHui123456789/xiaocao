package com.cjxy.park.service.customer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cjxy.park.bean.customer.SysEntryTable;
import com.cjxy.park.dao.customer.EntryTableDao;
import com.cjxy.park.dao.customer.EntryTableRepository;

@Service
public class EntryTableService {
	@Resource
	private EntryTableRepository entryTableRepository;
	@Resource
	private EntryTableDao entryTableDao;
	
	
	//查询所有
	public List<SysEntryTable> queryAll() {

		return entryTableRepository.findAll();
	}
	
	//分页
		public Page<SysEntryTable> queryEntryTable(SysEntryTable entryTable, int pageNo, int pageSize) throws Exception{
			return entryTableDao.queryEntryTable(entryTable, pageNo, pageSize);
		}
   //增加
		public String saveEntryTable(SysEntryTable entryTable) {
			if (null == entryTable.getCompanyName() || "".equals(entryTable.getCompanyName())) {
				return "企业名称不能为空";
			} else {
				List<SysEntryTable> ls = entryTableRepository.findByCompanyName(entryTable.getCompanyName());
				if (null != ls && ls.size() > 0) {
					return entryTable.getCompanyName() + "已经存在";
				}
			}
			if (null == entryTable.getEntryLocation()) {
				return "入驻位置不能为空";
			}
			if (null == entryTable.getIndustryClass()) {
				return "行业分类不能为空";
			}
			if (null == entryTable.getRegisterClass()) {
				return "登记注册不能为空";
			}
			if (null == entryTable.getListingSituation()) {
				return "上市情况不能为空";
			}
			if (null == entryTable.getLegalPerson()) {
				return "法人不能为空";
			}
			if (null == entryTable.getFixContact()) {
				return "固定联系人不能为空";
			}

			entryTableRepository.save(entryTable);
			return "success";
		}
   
		
		//修改
		public String updEntryTable(SysEntryTable entryTable) {
			String companyName = entryTable.getCompanyName();
			List<SysEntryTable> lsList = entryTableRepository.findByCompanyName(companyName);
			if (null == entryTable.getCompanyName() || "".equals(entryTable.getCompanyName())) {
				return "企业名称不能为空";
			} else if (lsList != null && lsList.size() > 0) {
				SysEntryTable sEntryTable = lsList.get(0);
				if (null == entryTable.getEntryLocation()) {
					return "入驻位置不能为空";
				}
				if (null == entryTable.getIndustryClass()) {
					return "行业分类不能为空";
				}
				if (null == entryTable.getRegisterClass()) {
					return "登记注册不能为空";
				}
				if (null == entryTable.getListingSituation()) {
					return "上市情况不能为空";
				}
				if (null == entryTable.getLegalPerson()) {
					return "法人不能为空";
				}
				if (null == entryTable.getFixContact()) {
					return "固定联系人不能为空";
				}

				entryTableRepository.save(entryTable);
			} else {
				return companyName + "已经存在";
			}
		   return"success";
		}

		//删除
		public void delEntryTable(Integer entryId) {
			entryTableRepository.deleteById(entryId);
			
		}

		
		
}
