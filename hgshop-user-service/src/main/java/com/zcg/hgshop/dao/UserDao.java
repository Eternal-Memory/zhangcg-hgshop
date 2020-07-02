package com.zcg.hgshop.dao;

import com.zcg.hgshop.domain.User;

public interface UserDao {
    
	int insert(User user);
	User getUserById(int id);
	User getUserByName(String name);
	User getUser(User user);
}
