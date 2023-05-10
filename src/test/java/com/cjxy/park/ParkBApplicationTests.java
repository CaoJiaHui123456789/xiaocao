package com.cjxy.park;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.service.space.BuildingService;

import javax.annotation.Resource;

@SpringBootTest
class ParkBApplicationTests {

	
	@Resource
	private BuildingService buildService;
	@Test
	void contextLoads() {
		
	}
	
//	@Test
	public void  testSaveBuilding() {
		try {
			SysBuilding bui = new SysBuilding();
			bui.setBuildingId(1);
			bui.setBuildingName("1号楼");
			bui.setFloorNumber(10);
			bui.setHouseNumber(100);
			bui.setBuildingArea(9000.6);
			bui.setPublicArea(1000.5);
			bui.setContractArea(8000.1);
			bui.setAvgArea(80.001);
			bui.setCusNumber(50);
			bui.setRentArea(4000.05);

			
			String id=buildService.saveBuilding(bui);
			System.out.println("id=" + id);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
