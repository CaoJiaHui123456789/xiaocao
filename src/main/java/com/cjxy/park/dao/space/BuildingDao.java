package com.cjxy.park.dao.space;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.dao.base.BaseDao;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;



@Repository
@Slf4j
public class BuildingDao {
	
	@Resource
	BaseDao baseDao;
	/**
	 * 查询
	 * @param staff
	 * @param pageSize
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<SysBuilding> queryBuilding(SysBuilding build,int pageNo,int pageSize,Double max,Double min) throws Exception{
		String hql="from SysBuilding";
		List<Object> params=new ArrayList<>();
		int  index =0;
		if (null!=build) {
			if (null!=build.getBuildingName()&&!"".equals(build.getBuildingName())&&build.getBuildingName().length()>0 ) {
				hql+= (index==0 ?" where ":" and") + " buildingName like ?" +(index++);
				params.add('%'+build.getBuildingName()+'%');
				if ((max!=null && max>=0) && (min!=null&& min<=max && min>=0)) {
					hql+= " and buildingArea >= ?"+(index++);
					hql+= " and buildingArea <= ?"+(index++);
					params.add(min);params.add(max);
				}else if((max!=null && max>=0)){
					hql+= " and buildingArea <= ?"+(index++);
					params.add(max);
				}else if((min!=null&& min>=0)){
					hql+= " and buildingArea >= ?"+(index++);
					params.add(min);
				}
			}else {
				hql+=" where ";
				if ((max!=null && max>=0) && (min!=null && min>=0)) {
					hql+= "buildingArea >= ?"+(index++)+" and buildingArea <= ?"+(index++);
					params.add(min);params.add(max);
				}else if((max!=null && max>=0)){
					hql+= "buildingArea <= ?"+(index++);
					params.add(max);
				}else if((min!=null && min>=0)){
					hql+= "buildingArea >= ?"+(index++);
					params.add(min);
				}else {
					hql+="1=1";
				}
			}
		}
		hql+=" order by buildingId";
		log.debug("queryBuilding:{}",hql);
		return baseDao.findByJpaPage(hql,params.toArray(), pageNo, pageSize);
	}

}


