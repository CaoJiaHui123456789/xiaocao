package com.cjxy.park.dao.space;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.cjxy.park.bean.space.SysHouse;
import com.cjxy.park.dao.base.BaseDao;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class HouseDao {

	@Resource
	BaseDao baseDao;

	/**
	 * 查询
	 * 
	 * @param staff
	 * @param pageSize
	 * @param pageSize
	 * @param bool
	 * @return
	 * @throws Exception
	 */
	public Page<SysHouse> queryHouse(SysHouse house, int pageNo, int pageSize) throws Exception {
		String hql = "from SysHouse";
		List<Object> params = new ArrayList<>();
		int index = 0;
		if (null != house) {
			if (null != house.getHouseCode() && !"".equals(house.getHouseCode()) && house.getHouseCode().length() > 0) {
				hql += (index == 0 ? " where " : " and") + " houseCode like ?" + (index++);
				params.add('%' + house.getHouseCode() + '%');
				if(house.getPayStatus()!=null){
					hql+= " and payStatus = ?"+(index++);
					params.add(house.getPayStatus());
					log.debug(hql);
				}
			} else if(house.getPayStatus()!=null){
				hql += (index == 0 ? " where " : " and") + " payStatus = ?" + (index++);
				params.add(house.getPayStatus());
				log.debug(hql);
			}
			
		}
		hql += " order by houseId";
//		System.out.println(hql);
		log.debug("queryHouse:{}", hql);
		return baseDao.findByJpaPage(hql, params.toArray(), pageNo, pageSize);
	}
}
