package com.cjxy.park.dao.space;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.space.SysAptDiagram;
import com.cjxy.park.bean.space.SysPark;
import com.cjxy.park.dao.base.BaseDao;

@Repository
public class AptDiagramDao {
	@Resource
	BaseDao baseDao;

	public Page<SysAptDiagram> queryAd(SysAptDiagram Ad, int pageNo, int pageSize) throws Exception{
		String hql="from SysAptDiagram";
		List<Object> params=new ArrayList<>();
		int  index =0;
		if (null!=Ad) {
			if (null!=Ad.getAdCode()&&!"".equals(Ad.getAdCode())&&Ad.getAdCode().length()>0 ) {
				
				hql+= (index==0 ?" where ":" and") + "adCode like ?" +(index++);
				params.add('%'+Ad.getAdCode()+'%');
			}
		}
		hql+=" order by adId";
		
		return baseDao.findByJpaPage(hql,params.toArray(), pageNo, pageSize);
	}
}
