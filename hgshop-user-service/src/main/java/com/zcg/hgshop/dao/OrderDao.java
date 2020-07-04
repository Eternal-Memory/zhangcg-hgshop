package com.zcg.hgshop.dao;

import java.util.List;

import com.zcg.hgshop.domain.OrderDetail;
import com.zcg.hgshop.domain.Orderz;

public interface OrderDao {
	int insert(Orderz orderz);
	int insertDetail(OrderDetail orderDetail);
	
	List<Orderz> selects(int uid);
	List<OrderDetail> selectDetail(int oid);
}
