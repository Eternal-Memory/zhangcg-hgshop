package com.zcg.hgshop.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.Sku;
import com.zcg.hgshop.domain.SkuVO;
import com.zcg.hgshop.domain.Spec;
import com.zcg.hgshop.domain.SpecOption;
import com.zcg.hgshop.domain.Spu;
import com.zcg.hgshop.service.SkuService;
import com.zcg.hgshop.service.SpecService;
import com.zcg.hgshop.service.SpuService;

@Controller
@RequestMapping("sku")
public class SkuController {
	@Reference
	SkuService skuService;
	@Reference
	SpuService spuService;
	@Reference
	SpecService specService;
	
	@Autowired
	HgFileUtils hgFileUtils;
	
	/**
	 * 查询所有列表
	 * @param model
	 * @param skuVO
	 * @return
	 */
	@RequestMapping("selects")
	public String selects(Model model,SkuVO skuVO) {
		PageInfo<Sku> info = skuService.selects(skuVO);
		model.addAttribute("info", info);
		model.addAttribute("skuVO", skuVO);
		return "sku/sku_list";
	}
	/**
	 * 跳转到添加页面
	 * @param model
	 * @param spuId
	 * @return
	 */
	@RequestMapping("toAdd")
	public String toAdd(Model model,int spuId) {
		Spu spu = spuService.getById(spuId);
		model.addAttribute("spu", spu);
		PageInfo<Spec> info = specService.selects(null, 1, 5);
		model.addAttribute("info", info);
		
		return "sku/sku_add";
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public int insert(Sku sku,@RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam("cartThumbnailFile") MultipartFile cartThumbnailFile) {
		List<SpecOption> list = sku.getOptions();
		for (int i = list.size()-1; i >=0; i--) {
			SpecOption option = list.get(i);
			if(0==option.getSpecId()) {
				list.remove(i);
			}
		}
		String upload = hgFileUtils.upload(imageFile);
		String upload1 = hgFileUtils.upload(cartThumbnailFile);
		sku.setImage(upload);
		sku.setCartThumbnail(upload1);
		int i = skuService.insert(sku);
		return i;
	}
	
	@RequestMapping("getSpecOptions")
	@ResponseBody
	public List<SpecOption> getSpecOptions(int specId){
		Spec spec = specService.getSpecById(specId);
		return spec.getOptions();
	}
}
