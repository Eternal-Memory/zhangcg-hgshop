package com.zcg.hgshop.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.Brand;
import com.zcg.hgshop.domain.Category;
import com.zcg.hgshop.domain.Spu;
import com.zcg.hgshop.domain.SpuVO;
import com.zcg.hgshop.service.BrandService;
import com.zcg.hgshop.service.CategoryService;
import com.zcg.hgshop.service.SpuService;

@Controller
@RequestMapping("spu")
public class SpuController {
	@Reference
	SpuService spuService;
	
	@Reference
	BrandService brandService;
	
	@Reference
	CategoryService categoryService;
	
	@Autowired
	HgFileUtils hgFileUtils;
	
	/**
	 * 查询spu列表
	 * @param model
	 * @param spuVO
	 * @return
	 */
	@RequestMapping("selects")
	public String selects(Model model,SpuVO spuVO) {
		PageInfo<Spu> info = spuService.selects(spuVO);
		List<Brand> brands = brandService.selects();
		model.addAttribute("info", info);
		model.addAttribute("brands", brands);
		model.addAttribute("spuVO", spuVO);
		return "/spu/spu_list";
	}
	
	@RequestMapping("toadd")
	public String add(Model model) {
		List<Category> list = categoryService.selects(0);
		List<Brand> brands = brandService.selects();
		model.addAttribute("brands", brands);
		model.addAttribute("list", list);
		return "/spu/spu_add";
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public int insert(Spu spu,MultipartFile file) {
		System.out.println("======"+spu);
		String upload = hgFileUtils.upload(file);
		spu.setSmallPic(upload);
		int i = spuService.add(spu);
		return i;
	}
	
	@RequestMapping("getById")
	public String getById(Model model,int id) {
		Spu spu = spuService.getById(id);
		System.out.println("======"+spu);
		System.out.println("*******"+spu.getBrandId());
		model.addAttribute("spu", spu);
		// 得到所有的品牌
		List<Brand> brands = brandService.selects();
		model.addAttribute("brands", brands);
		
		return "/spu/spu_update";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public int update(Spu spu,MultipartFile file) {
		String upload = hgFileUtils.upload(file);
		spu.setSmallPic(upload);
		int i = spuService.update(spu);
		return i;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public int delete(int[] ids) {
		int i = spuService.delete(ids);
		return i;
	}
}
