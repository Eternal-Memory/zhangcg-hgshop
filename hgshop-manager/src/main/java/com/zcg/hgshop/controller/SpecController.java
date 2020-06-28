package com.zcg.hgshop.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.Spec;
import com.zcg.hgshop.domain.SpecOption;
import com.zcg.hgshop.service.SpecService;

@Controller
@RequestMapping("spec")
public class SpecController {
	@Reference
	SpecService specService;
	
	/**
	 * 分页查询列表
	 */
	@RequestMapping("selects")
	public String selects(Model model,@RequestParam(defaultValue = "1")Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize,Spec spec) {
		PageInfo<Spec> info = specService.selects(spec, pageNum, pageSize);
		model.addAttribute("info", info);
		List<Spec> list = info.getList();
		for (Spec spec2 : list) {
			System.out.println(spec2);
		}
		return "spec/spec_list";
	}
	
	@GetMapping("add")
	public String add() {
		return "spec/spec_add";
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public int insert(Spec spec) {
		List<SpecOption> options = spec.getOptions();
		for(int i=options.size()-1;i>=0;i--) {
			SpecOption specOption = options.get(i);
			if (specOption.getOptionName()==null || "".equals(specOption.getOptionName())) {
				options.remove(i);
			}
		}
		int i = specService.insert(spec);
		return i;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public int delete(int[] ids) {
		int i = specService.delete(ids);
		return i;
	}
	
	@RequestMapping("toUpdate")
	public String toUpdate(Model model,int id) {
		Spec spec = specService.getSpecById(id);
		model.addAttribute("spec", spec);
		return "/spec/spec_update";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public int update(Spec spec) {
		int i = specService.update(spec);
		return i;
	}
	
}
