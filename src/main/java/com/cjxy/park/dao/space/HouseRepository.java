package com.cjxy.park.dao.space;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysHouse;

public interface HouseRepository extends JpaRepository<SysHouse, Integer> {
	public List<SysHouse> findByHouseCode(String houseCode);

	@Query(value = "from SysHouse where houseCode=:houseCode")
	public List<SysHouse> queryByHouseCode(String houseCode);

	public List<SysHouse> findByHouseId(Integer houseId);

	public List<SysHouse> findByBuilding(SysBuilding building);

}
