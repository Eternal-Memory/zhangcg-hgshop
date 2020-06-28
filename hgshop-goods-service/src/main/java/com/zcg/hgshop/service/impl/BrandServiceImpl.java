package com.zcg.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcg.hgshop.dao.BrandDao;
import com.zcg.hgshop.domain.Brand;
import com.zcg.hgshop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	BrandDao brandDao;
	
	@Override
	public List<Brand> selects() {
		// TODO Auto-generated method stub
		List<Brand> list = brandDao.selects();
		return list;
	}

	@Override
	public int insert(Brand brand) {
		// TODO Auto-generated method stub
		int i = brandDao.insert(brand);
		return i;
	}

	@Override
	public int update(Brand brand) {
		// TODO Auto-generated method stub
		int i = brandDao.update(brand);
		return 0;
	}

	@Override
	public int delete(int[] ids) {
		// TODO Auto-generated method stub
		int i = brandDao.delete(ids);
		return i;
	}

	@Override
	public Brand getBrandById(int id) {
		// TODO Auto-generated method stub
		Brand brand = brandDao.getBrandById(id);
		return brand;
	}

}
