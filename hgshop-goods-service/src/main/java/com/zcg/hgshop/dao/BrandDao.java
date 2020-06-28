package com.zcg.hgshop.dao;

import java.util.List;

import com.zcg.hgshop.domain.Brand;

public interface BrandDao {
	List<Brand> selects();
	int insert(Brand brand);
	int update(Brand brand);
	int delete(int[] ids);
	Brand getBrandById(int id);
}
