package com.cjxy.park.dao.customer;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjxy.park.bean.customer.SysEntryTable;

public interface EntryTableRepository  extends JpaRepository<SysEntryTable,Integer>{

	List<SysEntryTable> findByCompanyName(String companyName);

  
}
