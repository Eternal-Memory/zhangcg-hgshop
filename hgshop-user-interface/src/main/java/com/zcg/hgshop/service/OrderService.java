package com.zcg.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.OrderDetail;
import com.zcg.hgshop.domain.Orderz;

public interface OrderService {
	/**
	 * 根据购物车id 生成订单
	 * @param cartIds
	 * @param uid
	 * @return
	 */
	int createOrder(int[] cartIds, String address ,Integer uid);
	PageInfo<Orderz> selects(int uid,int page);
	List<OrderDetail> selectDetail(int oid);
}
