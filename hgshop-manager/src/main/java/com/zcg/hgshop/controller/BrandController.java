package com.zcg.hgshop.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcg.hgshop.domain.Brand;
import com.zcg.hgshop.service.BrandService;

@Controller
@RequestMapping("brand")
public class BrandController {
	@Reference
	BrandService brandService;
	
	@RequestMapping("selects")
	public String selects(Model model) {
		List<Brand> list = brandService.selects();
		model.addAttribute("list", list);
		return "brand/brand_list";
	}
	
	
}
