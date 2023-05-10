package com.cjxy.park.dao.setUp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.setUp.SysNewsCont;
import com.cjxy.park.dao.base.BaseDao;

import lombok.extern.slf4j.Slf4j;
@Repository
@Slf4j
public class NewsContDao {
	@Resource
	BaseDao baseDao;

	public Page<SysNewsCont> queryNewsCont(SysNewsCont newsCont, int pageNo, int pageSize) throws Exception{
		String hql="from SysNewsCont";
		List<Object> params=new ArrayList<>();
		int  index =0;
		if (null!=newsCont) {
			if (null!=newsCont.getNewsName()&&!"".equals(newsCont.getNewsName())&&newsCont.getNewsName().length()>0 ) {
				hql+= (index==0 ?" where ":" and") + " newsName like ?" +(index++);
				params.add('%'+newsCont.getNewsName()+'%');
			}
		}
		hql+=" order by newcontId";
		log.debug("queryBuilding:{}",hql);
		return baseDao.findByJpaPage(hql,params.toArray(), pageNo, pageSize);
	}

	

}