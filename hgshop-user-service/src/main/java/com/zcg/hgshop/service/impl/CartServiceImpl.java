package com.zcg.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcg.hgshop.dao.CartDao;
import com.zcg.hgshop.domain.Cart;
import com.zcg.hgshop.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartDao cartDao;
	
	@Override
	public int insert(Cart cart) {
		// TODO Auto-generated method stub
		int i = cartDao.insert(cart);
		return i;
	}

	@Override
	public int delete(int[] ids) {
		// TODO Auto-generated method stub
		int i = cartDao.delete(ids);
		return i;
	}

	@Override
	public List<Cart> selects(int uid) {
		// TODO Auto-generated method stub
		List<Cart> list = cartDao.selects(uid);
		return list;
	}

	@Override
	public List<Cart> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		List<Cart> list = cartDao.getByIds(ids);
		return list;
	}

}
