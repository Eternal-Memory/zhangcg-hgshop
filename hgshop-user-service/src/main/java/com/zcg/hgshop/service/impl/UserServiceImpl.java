package com.zcg.hgshop.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcg.hgshop.dao.UserDao;
import com.zcg.hgshop.domain.User;
import com.zcg.hgshop.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	/**
	 * 注册
	 */
	@Override
	public User register(User user) {
		User byName = userDao.getUserByName(user.getName());
		if(byName!=null) {
			return null;
		}
		// 计算密码的密文  加盐
		String md5Hex = DigestUtils.md5Hex(user.getPassword()+user.getName()+"123456");
		user.setPassword(md5Hex);
		int i = userDao.insert(user);
		if(i<1) {
			return null;
		}
		
		User u = userDao.getUserById(user.getUid());
		System.out.println("========"+u);
		return u;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		// 计算密码的密文  加盐
		String md5Hex = DigestUtils.md5Hex(user.getPassword()+user.getName()+"123456");
		user.setPassword(md5Hex);
		User u = userDao.getUser(user);
		return u;
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		User user = userDao.getUserByName(name);
		return user;
	}

}
