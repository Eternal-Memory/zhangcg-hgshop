package com.zcg.hgshop.service;

import java.util.List;

import com.zcg.hgshop.domain.Brand;

public interface BrandService {
	List<Brand> selects();
	int insert(Brand brand);
	int update(Brand brand);
	int delete(int[] ids);
	Brand getBrandById(int id);
}
