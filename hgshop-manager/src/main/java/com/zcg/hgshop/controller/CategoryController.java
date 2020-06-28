package com.zcg.hgshop.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcg.hgshop.domain.Category;
import com.zcg.hgshop.service.CategoryService;
@RequestMapping("category")
@Controller
public class CategoryController {
	@Reference
	CategoryService categoryService;
	
	@RequestMapping("index")
	public String index() {
		return "/category/category_list";
	}
	
	@RequestMapping("selects")
	@ResponseBody
	public List<Category> selects() {
		List<Category> list = categoryService.selects(0);
		return list;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public int add(Category category) {
		int i = categoryService.add(category);
		return i;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public int update(Category category) {
		int i = categoryService.update(category);
		return i;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public int delete(int id) {
		int i = categoryService.delete(id);
		return i;
	}
}
