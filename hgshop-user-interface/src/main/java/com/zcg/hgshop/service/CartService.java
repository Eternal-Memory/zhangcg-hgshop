package com.zcg.hgshop.service;

import java.util.List;

import com.zcg.hgshop.domain.Cart;
/**
 * 购物车的服务
 * @author hp
 *
 */
public interface CartService {
	int insert(Cart cart);
	
	int delete(int[] ids);
	//用户Id
	List<Cart> selects(int uid);
	//
	List<Cart> getByIds(int[] ids);
}
