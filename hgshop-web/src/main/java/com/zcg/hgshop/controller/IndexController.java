package com.zcg.hgshop.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.Category;
import com.zcg.hgshop.domain.Sku;
import com.zcg.hgshop.domain.Spu;
import com.zcg.hgshop.domain.SpuVO;
import com.zcg.hgshop.service.CategoryService;
import com.zcg.hgshop.service.SkuService;
import com.zcg.hgshop.service.SpuService;

@Controller
public class IndexController {
	@Reference
	SpuService spuService;
	@Reference
	CategoryService categoryService;
	@Reference 
	SkuService skuService;
	
	@RequestMapping({"/","index"})
	public String Index(Model model,SpuVO spuVO) {
		spuVO.setPageSize(12);
		PageInfo<Spu> info = spuService.selects(spuVO);
		model.addAttribute("info", info);
		model.addAttribute("spuVO", spuVO);
		return "index";
	}
	
	@RequestMapping("catData")
	@ResponseBody
	public List<Category> getData(){
		//获取到所有分类的数据
		List<Category> list = categoryService.selects(0);
		return list;
	}
	
	@RequestMapping("spu")
	public String spuDetail(Model model,int spuId) {
		//获取spu的信息
		Spu spu = spuService.getById(spuId);
		//获取sku的列表
		List<Sku> skus=skuService.listsDetailBySpu(spuId);
		model.addAttribute("spu", spu);
		model.addAttribute("skus", skus);
		return "spudetail";
	}
	
	
}
