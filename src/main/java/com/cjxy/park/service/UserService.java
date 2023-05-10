package com.cjxy.park.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cjxy.park.bean.system.SysUser;
import com.cjxy.park.dao.UserDao;
import com.cjxy.park.dao.UserRepository;

@Service
public class UserService {
	@Resource
	private UserRepository userRepository;
	@Resource
	private UserDao userDao;

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<SysUser> queryAll() {
		return userRepository.findAll();
	}

	/**
	 * 保存一个用户
	 * 
	 * @return
	 */
	public Integer saveUser(SysUser user) {
		return userRepository.save(user).getUserId();
	}

	public void updUser(SysUser user) {
		userRepository.save(user);
	}

	public void delUser(Integer userId) {
		userRepository.deleteById(userId);
	}

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public SysUser getUserByCode(String userCode) {
		return userRepository.findByUserCode(userCode);
	}

	public Page<SysUser> queryUser(SysUser user, int pageNo, int pageSize) throws Exception{
		
		return userDao.queryUser(user,pageNo,pageSize);
	}

}
