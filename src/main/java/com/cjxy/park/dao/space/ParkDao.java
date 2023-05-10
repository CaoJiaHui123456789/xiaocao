package com.cjxy.park.dao.space;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.space.SysPark;
import com.cjxy.park.dao.base.BaseDao;


@Repository
public class ParkDao {

	@Resource
	BaseDao baseDao;

	public Page<SysPark> queryPark(SysPark park, int pageNo, int pageSize) throws Exception{
		String hql="from SysPark";
		List<Object> params=new ArrayList<>();
		int  index =0;
		if (null!=park) {
			if (null!=park.getParkName()&&!"".equals(park.getParkName())&&park.getParkName().length()>0 ) {
				hql+= (index==0 ?" where ":" and") + " parkName like ?" +(index++);
				params.add('%'+park.getParkName()+'%');
			}
		}
		hql+=" order by parkId";
		System.out.println(hql);
		return baseDao.findByJpaPage(hql,params.toArray(), pageNo, pageSize);
	}

	

}
