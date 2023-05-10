
package com.cjxy.park.dao.space;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjxy.park.bean.space.SysPark;



public interface ParkRepository extends JpaRepository<SysPark,Integer>{
	
	public List<SysPark> findByParkName(String parkName);
	
	@Query(value = "from SysPark where parkName=:parkName")
	public List<SysPark> queryByParkName(String parkName);

	public List<SysPark> findByParkId(Integer parkId);


}
