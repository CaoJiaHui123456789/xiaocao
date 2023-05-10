package com.cjxy.park.dao.setUp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.setUp.SysNewsTable;
import com.cjxy.park.dao.base.BaseDao;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class NewsTableDao {

	@Resource
	BaseDao baseDao;
	
	public Page<SysNewsTable> queryNewsTable(SysNewsTable table, int pageNo, int pageSize) throws Exception{
		
		String hql="from SysNewsTable";
		List<Object> params=new ArrayList<>();
		int  index =0;
		if (null != table) {
			if (null != table.getNewsSum() && !"".equals(table.getNewsSum()) && table.getNewsSum().length() > 0) {
				hql += (index == 0 ? " where " : " and") + " newsSum like ?" + (index++);
				params.add('%' + table.getNewsSum() + '%');
				if(table.getNewsRank()!=null){
					hql+= " and newsRank = ?"+(index++);
					params.add(table.getNewsRank());
					log.debug(hql);
				}
			} else if(table.getNewsRank()!=null){
				hql += (index == 0 ? " where " : " and") + " newsRank = ?" + (index++);
				params.add(table.getNewsRank());
				log.debug(hql);
			}
		}
		hql+=" order by newsId";
		log.debug("queryBuilding:{}",hql);
		return baseDao.findByJpaPage(hql,params.toArray(), pageNo, pageSize);
	}

	}
