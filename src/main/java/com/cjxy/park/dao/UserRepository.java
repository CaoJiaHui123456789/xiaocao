package com.cjxy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjxy.park.bean.system.SysUser;

public interface UserRepository extends JpaRepository<SysUser,Integer>{
public List<SysUser> findByUserName(String userName);
	
	@Query(value = "from SysUser where userName=:userName")
	public List<SysUser> queryByUserName(String userName);


	public SysUser findByUserCode(String userCode);

}
