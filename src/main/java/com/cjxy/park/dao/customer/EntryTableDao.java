package com.cjxy.park.dao.customer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.customer.SysEntryTable;
import com.cjxy.park.dao.base.BaseDao;

import lombok.extern.slf4j.Slf4j;
@Repository
@Slf4j
public class EntryTableDao {

	@Resource
	BaseDao baseDao;
	
	public Page<SysEntryTable> queryEntryTable(SysEntryTable entryTable, int pageNo, int pageSize) throws Exception{
		String hql="from SysEntryTable";
		List<Object> params=new ArrayList<>();
		int index = 0;
		if (null != entryTable) {
				if (null!=entryTable.getCompanyName()&&!"".equals(entryTable.getCompanyName())&&entryTable.getCompanyName().length()>0 ) {
					hql+= (index==0 ?" where ":" and") + " companyName like ?" +(index++);
					params.add('%'+entryTable.getCompanyName()+'%');
				}
				if (null!=entryTable.getIndustryClass()&&!"".equals(entryTable.getIndustryClass())&&entryTable.getIndustryClass().length()>0 ) {
					hql+= (index==0 ?" where ":" and") + " industryClass like ?" +(index++);
					params.add('%'+entryTable.getIndustryClass()+'%');
				}
			}
			hql+=" order by entryId";
			log.debug("querCustomer:{}",hql);
			return baseDao.findByJpaPage(hql,params.toArray(), pageNo, pageSize);
		}
		
}