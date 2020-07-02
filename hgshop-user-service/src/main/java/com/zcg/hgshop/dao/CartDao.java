package com.zcg.hgshop.dao;

import java.util.List;

import com.zcg.hgshop.domain.Cart;

public interface CartDao {
	int insert(Cart cart);
	
	int delete(int[] ids);
	//用户Id
	List<Cart> selects(int uid);
	//
	List<Cart> getByIds(int[] ids);
}
