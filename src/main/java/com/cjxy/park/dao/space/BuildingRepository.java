package com.cjxy.park.dao.space;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysPark;

public interface BuildingRepository extends JpaRepository<SysBuilding,Integer> {
	
	public List<SysBuilding> findByBuildingName(String buildingName);
	
	@Query(value = "from SysBuilding where buildingName=:buildingName")
	public List<SysBuilding> queryByBuildingName(String buildingName);

	public List<SysBuilding> findByBuildingId(Integer buildingId);

	public List<SysBuilding> findByPark(SysPark park);

}

