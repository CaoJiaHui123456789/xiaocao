package com.cjxy.park.service.space;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cjxy.park.bean.space.SysAptDiagram;
import com.cjxy.park.bean.space.SysBuilding;
import com.cjxy.park.bean.space.SysHouse;
import com.cjxy.park.dao.base.MinioDao;
import com.cjxy.park.dao.space.BuildingRepository;
import com.cjxy.park.dao.space.HouseDao;
import com.cjxy.park.dao.space.HouseRepository;

@Service
public class HouseService {
	@Resource
	private HouseRepository houseRepository;
	@Resource
	private HouseDao houseDao;
	@Resource
	private MinioDao minioDao;
	@Resource
	private BuildingRepository buildingRepository;

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<SysHouse> queryAll() {
		return houseRepository.findAll();
	}

	//根据buildingId进行查询
	public List<SysHouse> getHouseBybuildingId(Integer buildingId){
		SysBuilding building=new SysBuilding();
		building.setBuildingId(buildingId);
		List<SysHouse> lsList = houseRepository.findByBuilding(building);
		return lsList;
	}
	
	
	// 保存
	public String saveHouse(SysHouse house) {
		SysBuilding building = house.getBuilding();
		SysAptDiagram aptdiagram = house.getAptdiagram();
		if (null == house.getHouseCode() || "".equals(house.getHouseCode())) {
			return "房间编号不能为空";
		} else {
			List<SysHouse> ls = houseRepository.findByHouseCode(house.getHouseCode());
			if (null != ls && ls.size() > 0) {
				return house.getHouseCode() + "已经存在";
			}
		}
		if (null == building.getBuildingId()) {
			return "楼栋名称不能为空";
		}
		if (null == aptdiagram.getAdId()) {
			return "户型图不能为空";
		}
		houseRepository.save(house);
		return "success";
		

	}

	// 修改
	public String updHouse(SysHouse house) {
		String houseCode = house.getHouseCode();
		SysBuilding building = house.getBuilding();
		SysAptDiagram aptdiagram = house.getAptdiagram();
		List<SysHouse> lsList = houseRepository.findByHouseCode(houseCode);
		if (null == house.getHouseCode() || "".equals(house.getHouseCode())) {
			return "房间编号不能为空";
		} else if (lsList != null && lsList.size() > 0) {
			SysHouse sHouse = lsList.get(0);
			if (sHouse.getHouseId() == house.getHouseId()) {
				if (null == building.getBuildingId()) {
					return "楼栋名称不能为空";
				}
				if (null == aptdiagram.getAdId()) {
					return "户型图不能为空";
				}
				houseRepository.save(house);
			} else {
				return houseCode + "已经存在";
			}

		} else {
			if (null == building.getBuildingId()) {
				return "楼栋名称不能为空";
			}
			if (null == aptdiagram.getAdId()) {
				return "户型图不能为空";
			}
			houseRepository.save(house);
		}
		return "success";
	}

	// 删除
	public void delHouse(Integer houseId) {
		houseRepository.deleteById(houseId);
	}

	/**
	 * 
	 * @param houseCode
	 * @param
	 * @return
	 */
	// 查询
	public List<SysHouse> getHouseByCode(String houseCode) {
		return houseRepository.findByHouseCode(houseCode);
	}

	// 分页
		public Page<SysHouse> queryHouse(SysHouse house, int pageNo, int pageSize)throws Exception{
			return houseDao.queryHouse(house, pageNo,pageSize);
		}

	// 载入图片
	public byte[] downloadFile(String bucketName, String filePath) {
		return minioDao.downloadFile(bucketName, filePath);
	}


}
