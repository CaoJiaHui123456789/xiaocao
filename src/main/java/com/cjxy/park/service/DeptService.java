package com.cjxy.park.service;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.cjxy.park.bean.system.SysDept;
import com.cjxy.park.dao.DeptDao;
import com.cjxy.park.dao.DeptRepository;
import com.cjxy.park.dao.base.MinioDao;



@Service
public class DeptService {
	@Resource
	private DeptRepository deptRepository;
	@Resource
	private DeptDao deptDao;
	@Resource
	private MinioDao minioDao;
	
	/**
	 * 查询所有
	 * @return
	 */
	public  List<SysDept> queryAll(){
		return deptRepository.findAll();
	}
	
	public List<SysDept> getDeptByNameAndCode(String deptName,String deptCode){
		return deptRepository.findByDeptNameAndDeptCode(deptName, deptCode);
	}
	
	//分页
	public Page<SysDept> queryDept(SysDept dept, int pageNo, int pageSize) throws Exception{
		return deptDao.queryDept(dept, pageNo, pageSize);
	}
	
	//保存
		public String saveDept(SysDept dept) {
			if (null == dept.getDeptCode() || "".equals(dept.getDeptCode())) {
				return "部门编号不能为空";
			} else {
				List<SysDept> ls = deptRepository.findByDeptCode(dept.getDeptCode());
				if (null != ls && ls.size() > 0) {
					return dept.getDeptCode() + "已经存在";
				}
			}
			if (null == dept.getDeptName()) {
				return "部门名称不能为空";
			}
			if (null == dept.getDeptLocation()) {
				return "部门位置不能为空";
			}
			if (null == dept.getDeptTel()) {
				return "部门电话不能为空";
			}
			

			deptRepository.save(dept);
			return "success";
		}
		
		//修改
		public String updDept(SysDept dept) {
			String deptCode = dept.getDeptCode();
			List<SysDept> lsList = deptRepository.findByDeptCode(deptCode);
			if (null == dept.getDeptCode() || "".equals(dept.getDeptCode())) {
				return "部门编号不能为空";
			} else if (lsList != null && lsList.size() > 0) {
				SysDept sDept = lsList.get(0);
				if (null == dept.getDeptName()) {
					return "部门名称不能为空";
				}
				if (null == dept.getDeptLocation()) {
					return "部门位置不能为空";
				}
				if (null == dept.getDeptTel()) {
					return "部门电话不能为空";
				}
				
				deptRepository.save(dept);
			} else {
				return deptCode + "已经存在";
			}
		   return"success";
		}

		//删除
		public void delDept(Integer deptId) {
			deptRepository.deleteById(deptId);
		}
}
