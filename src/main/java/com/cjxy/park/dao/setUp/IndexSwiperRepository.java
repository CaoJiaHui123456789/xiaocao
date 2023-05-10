package com.cjxy.park.dao.setUp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjxy.park.bean.setUp.SysIndexSwiper;

public interface IndexSwiperRepository extends JpaRepository<SysIndexSwiper,Integer> {
	@Query(value = "SELECT COUNT(*) FROM SysIndexSwiper")
	public Integer querySwiperNums();
	
	@Query(value = "SELECT indexSwiperImgPath FROM SysIndexSwiper ORDER BY indexSwiperId")
	public List<String> queryPath();
	
	@Query(value = "SELECT indexSwiperId FROM SysIndexSwiper ORDER BY indexSwiperId")
	public List<Integer> queryId();
	
	@Query(value = "SELECT indexSwiperImgPath FROM SysIndexSwiper WHERE indexSwiperId =:id")
	public String getPathById(Integer id);
	

}
