package com.zcg.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcg.hgshop.dao.CategoryDao;
import com.zcg.hgshop.domain.Category;
import com.zcg.hgshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public int add(Category category) {
		// TODO Auto-generated method stub
		int i = categoryDao.add(category);
		return i;
	}

	@Override
	public int update(Category category) {
		// TODO Auto-generated method stub
		int i = categoryDao.update(category);
		return i;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int i = categoryDao.delete(id);
		return i;
	}

	@Override
	public List<Category> selects(int parentId) {
		// TODO Auto-generated method stub
		List<Category> list = categoryDao.selects(parentId);
		return list;
	}

}
