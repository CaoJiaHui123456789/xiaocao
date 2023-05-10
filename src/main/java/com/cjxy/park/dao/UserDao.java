package com.cjxy.park.dao;

import java.util.ArrayList;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.system.SysUser;
import com.cjxy.park.dao.base.BaseDao;

@Repository
public class UserDao {
	@Resource
	BaseDao baseDao;

	public Page<SysUser> queryUser(SysUser user, int pageNo, int pageSize) throws Exception{
		String hql="from SysUser";
		List<Object> params=new ArrayList<>();
		int  index =0;
		if (null!=user) {
			if (null!=user.getUserName()&&!"".equals(user.getUserName())&&user.getUserName().length()>0 ) {
				hql+= (index==0 ?" where ":" and") + " userName like ?" +(index++);
				params.add('%'+user.getUserName()+'%');
			}
		}
		if (null!=user) {
			if (null!=user.getUserCode()&&!"".equals(user.getUserCode())&&user.getUserCode().length()>0 ) {
				hql+= (index==0 ?" where ":" and") + " userCode like ?" +(index++);
				params.add('%'+user.getUserCode()+'%');
			}
		}
		hql+=" order by userId";
		System.out.println(hql);
		return baseDao.findByJpaPage(hql,params.toArray(), pageNo, pageSize);
	}

	

}


