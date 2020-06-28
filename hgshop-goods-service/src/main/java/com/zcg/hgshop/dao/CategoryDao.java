package com.zcg.hgshop.dao;

import java.util.List;

import com.zcg.hgshop.domain.Category;

public interface CategoryDao {
	int add(Category category);
	int update(Category category);
	int delete(int id);
	List<Category> selects(int parentId);
	
}
