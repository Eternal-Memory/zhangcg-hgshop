package com.zcg.hgshop.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.dao.CartDao;
import com.zcg.hgshop.dao.OrderDao;
import com.zcg.hgshop.domain.Cart;
import com.zcg.hgshop.domain.OrderDetail;
import com.zcg.hgshop.domain.Orderz;
import com.zcg.hgshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDao orderDao;
	@Autowired
	CartDao cartDao;
	
	
	@Override
	public PageInfo<Orderz> selects(int uid, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		List<Orderz> list = orderDao.selects(uid);
		return new PageInfo<Orderz>(list);
	}

	@Override
	public List<OrderDetail> selectDetail(int oid) {
		// TODO Auto-generated method stub
		List<OrderDetail> list = orderDao.selectDetail(oid);
		return list;
	}

	@Override
	public int createOrder(int[] cartIds, String address, Integer uid) {
		// 得到购物车和商品的信息
		List<Cart> lists = cartDao.getByIds(cartIds);
		//订单明细
		ArrayList<OrderDetail> orderList = new ArrayList<OrderDetail>();
		//整个订单的价格
		BigDecimal orderTotal = new BigDecimal(0);
		int result=0;
		for (Cart cart : lists) {
			OrderDetail detail = new OrderDetail();
			//购买数量
			detail.setPnum(cart.getPnum());
			detail.setSkuid(cart.getSku().getId());
			// 计算单项总价格  这里就是乘法
			detail.setTotal(cart.getSku().getPrice().multiply(new BigDecimal(cart.getPnum())));
			orderList.add(detail);
			//订单价格的累计
			orderTotal=orderTotal.add(detail.getTotal());
		}
		// 生成订单
		Orderz orderz = new Orderz();
		orderz.setSumtotal(orderTotal);
		orderz.setAddress(address);
		orderz.setUid(uid);
		
		//将主表插入数据库  自动生成主键
		result += orderDao.insert(orderz);
		
		//插入明细
		for (OrderDetail orderDetail : orderList) {
			orderDetail.setOid(orderz.getOid());
			result += orderDao.insertDetail(orderDetail);
		}
		
		//删除购物车中的数据
		result += cartDao.delete(cartIds);
		return result;
	}
	
	
}
