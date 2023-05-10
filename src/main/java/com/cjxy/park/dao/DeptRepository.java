package com.cjxy.park.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cjxy.park.bean.system.SysDept;

public interface DeptRepository extends JpaRepository<SysDept,Integer>{

	List<SysDept> findByDeptNameAndDeptCode(String deptName, String deptCode);

	List<SysDept> findByDeptCode(String deptCode);


}
