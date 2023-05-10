package com.cjxy.park.dao;

import java.util.ArrayList;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.system.SysDept;
import com.cjxy.park.dao.base.BaseDao;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DeptDao {

	@Resource
	BaseDao baseDao;
	
	public Page<SysDept> queryDept(SysDept dept, int pageNo, int pageSize) throws Exception{
		
		String hql="from SysDept";
		List<Object> params=new ArrayList<>();
		int index = 0;
		if (null != dept) {
				if (null!=dept.getDeptName()&&!"".equals(dept.getDeptName())&&dept.getDeptName().length()>0 ) {
					hql+= (index==0 ?" where ":" and") + " deptName like ?" +(index++);
					params.add('%'+dept.getDeptName()+'%');
				}
				if (null!=dept.getDeptCode()&&!"".equals(dept.getDeptCode())&&dept.getDeptCode().length()>0 ) {
					hql+= (index==0 ?" where ":" and") + "  DeptCode like ?" +(index++);
					params.add('%'+dept.getDeptCode()+'%');
				}
			}
			hql+=" order by deptId";
			log.debug("querDept:{}",hql);
			return baseDao.findByJpaPage(hql,params.toArray(), pageNo, pageSize);
		}
		
}
