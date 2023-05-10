package com.cjxy.park.dao.setUp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjxy.park.bean.setUp.SysNewsCont;


public interface NewsContRepository extends JpaRepository<SysNewsCont,Integer>{
public List<SysNewsCont> findByNewsName(String newsName);
	
	@Query(value = "from SysNewsCont where newsName=:newsName")
	public List<SysNewsCont> queryByNewsName(String newsName);
}
