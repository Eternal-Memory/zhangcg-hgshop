package com.zcg.hgshop.service;

import com.zcg.hgshop.domain.User;

public interface UserService {
	//注册
	User register(User user);
	//登录
	User login(User user);
	//根据用户名查找用户是否存在，注册时验证唯一性
	User getUserByName(String name);
}
