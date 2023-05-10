package com.cjxy.park.dao.setUp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjxy.park.bean.setUp.SysNewsTable;

public interface NewsTableRepository extends JpaRepository<SysNewsTable,Integer> {

	List<SysNewsTable> findByNewsRank(String newsTable);
     
	List<SysNewsTable> findByNewsCode(String newsCode);
	
	@Query(value = "from SysNewsTable where newsTable=:newsTable")
	public List<SysNewsTable> queryByNewsRank(String newsTable);
	
	@Query(value = "from SysNewsTable order by newsRank ASC")
	public List<SysNewsTable> queryAllOrderByNewsRank();
	
	
}
