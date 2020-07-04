package com.zcg.hgshop.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.Category;
import com.zcg.hgshop.domain.EsSpu;
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
	@Autowired
	private RedisTemplate<String, PageInfo<Spu>> redisTemplate;
	
	@Autowired
	ElSearchUtil<EsSpu> elSearchUtil;
	
	/**
	 * 访问商城主页面
	 * @param model
	 * @param spuVO
	 * @return
	 */
	@RequestMapping({"/","index"})
	public String Index(Model model,SpuVO spuVO) {
		spuVO.setPageSize(12);
		model.addAttribute("spuVO", spuVO);
		ValueOperations<String, PageInfo<Spu>> opsForValue = redisTemplate.opsForValue();
		//高频度访问，需要使用缓存
		if(spuVO.getPageNum()==1 && spuVO.getCategoryId()==0) {
			Boolean hasKey= redisTemplate.hasKey("firstPage");
			if(hasKey) {
				PageInfo<Spu> info = opsForValue.get("firstPage");
				model.addAttribute("info", info);
				return "index";
			}else {
				//缓存中不存在，在数据库中获取
				PageInfo<Spu> info = spuService.selects(spuVO);
				opsForValue.set("firstPage", info,3000,TimeUnit.SECONDS);
				model.addAttribute("info", info);
				return "index";
			}
		}else {
			if(spuVO.getCategoryId()!=0) {
				Boolean hasKey= redisTemplate.hasKey("fenlei");
				if(hasKey) {
					PageInfo<Spu> info = opsForValue.get("fenlei");
					model.addAttribute("info", info);
					return "index";
				}else {
					//缓存中不存在，在数据库中获取
					PageInfo<Spu> info = spuService.selects(spuVO);
					opsForValue.set("fenlei", info,3000,TimeUnit.SECONDS);
					model.addAttribute("info", info);
					return "index";
				}
			}
			PageInfo<Spu> info = spuService.selects(spuVO);
			model.addAttribute("info", info);
			return "index";
		}
		
	}
	/**
	 * 获取分类的数据
	 * @return
	 */
	@RequestMapping("catData")
	@ResponseBody
	public List<Category> getData(){
		//获取到所有分类的数据
		List<Category> list = categoryService.selects(0);
		return list;
	}
	/**
	 * 点击商品，跳转到商品详情页面
	 * @param model
	 * @param spuId
	 * @return
	 */
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
	
	/**
	 * 高亮查询
	 * @param model
	 * @param spuVO
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model,SpuVO spuVO) {
		Date startTime= new Date();
		AggregatedPage<EsSpu> page = elSearchUtil.queryObjects(EsSpu.class, spuVO.getPageNum(), spuVO.getPageSize(),
				new String[] {"goodsName","caption","categoryName","brandName"}, spuVO.getKey());
		model.addAttribute("spuVO", spuVO);
		model.addAttribute("page", page);
		
		Date endTime = new Date();
		long consumerTime= endTime.getTime()-startTime.getTime();
		model.addAttribute("consumerTime", consumerTime);
		return "query";
	}
	
}
