package com.cjxy.park.service.space;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysPark;
import com.cjxy.park.dao.space.BuildingDao;
import com.cjxy.park.dao.space.BuildingRepository;
import com.cjxy.park.dao.space.ParkRepository;

import javax.annotation.Resource;

@Service
public class BuildingService {
	@Resource
	private BuildingRepository buildingRepository;
	@Resource
	private BuildingDao buildingDao;
	@Resource
	private ParkRepository parkRepository;
	/**
	 * 查询所有
	 * @return
	 */
	public List<SysBuilding> queryAll(){
		return buildingRepository.findAll();
	}
	
	//根据parkId进行查询
	public List<SysBuilding> getBuildingByparkId(Integer parkId){
		SysPark park=new SysPark();
		park.setParkId(parkId);
		List<SysBuilding> lsList = buildingRepository.findByPark(park);
		return lsList;
	}
	
	
//	//保存
//	public Integer saveBuilding(SysBuilding build) {
//		String buildName=build.getBuildingName();
//		List<SysBuilding> lsList=buildingRepository.findByBuildingName(buildName);
//		if (lsList!=null&&lsList.size()>0) {
//			return -1;
//		}else {
//			return buildingRepository.save(build).getBuildingId();
//		}	
//		
//	}
	
	//保存
	public String saveBuilding(SysBuilding building) {
		SysPark park = building.getPark();

		if (null == building.getBuildingName() || "".equals(building.getBuildingName())) {
			return "楼栋名称不能为空";
		} else {
			List<SysBuilding> ls = buildingRepository.findByBuildingName(building.getBuildingName());
			if (null != ls && ls.size() > 0) {
				return building.getBuildingName() + "已经存在";
			}
		}
		if (null == park.getParkId()) {
			return "所属园区不能为空";
		}
		if (null == building.getBuildingArea()) {
			return "建筑面积不能为空";
		}

		buildingRepository.save(building);
		return "success";
	}

	// 修改
	public String updBuilding(SysBuilding building) {
		SysPark park = building.getPark();
		String buildName = building.getBuildingName();
		List<SysBuilding> lsList = buildingRepository.findByBuildingName(buildName);
		if (null == building.getBuildingName() || "".equals(building.getBuildingName())) {
			return "楼栋名称不能为空";
		} else if (lsList != null && lsList.size() > 0) {
			SysBuilding sBuilding = lsList.get(0);
			if (sBuilding.getBuildingId() == building.getBuildingId()) {
				if (null == park.getParkId()) {
					return "所属园区不能为空";
				}
				if (null == building.getBuildingArea()) {
					return "建筑面积不能为空";
				}
				buildingRepository.save(building);
			} else {
				return buildName + "已经存在";
			}

		} else {
			if (null == park.getParkId()) {
				return "所属园区不能为空";
			}
			if (null == building.getBuildingArea()) {
				return "建筑面积不能为空";
			}
			buildingRepository.save(building);
		}
		return "success";
	}
	
	//删除
	public void delBuilding(Integer buildingId) {
		buildingRepository.deleteById(buildingId);
	}
	/**
	 * 
	 * @param buildingName
	 * @param 
	 * @return
	 */
	//查询
	public List<SysBuilding> getBuildingByName(String buildingName){
		return buildingRepository.findByBuildingName(buildingName);
	}
	
	//分页
	public Page<SysBuilding> queryBuilding(SysBuilding build,int pageNo,int pageSize,Double max,Double min) throws Exception{
		return buildingDao.queryBuilding(build, pageNo, pageSize,max,min);
	}



	
	

}