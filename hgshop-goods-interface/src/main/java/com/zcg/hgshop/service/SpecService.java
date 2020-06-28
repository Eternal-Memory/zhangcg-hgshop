package com.zcg.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.Spec;
import com.zcg.hgshop.domain.SpecOption;

public interface SpecService {
	//查询列表
	PageInfo<Spec> selects(Spec spec,Integer pageNum,Integer pageSize);
	//添加
	int insert(Spec spec);
	//修改
	int update(Spec spec);
	//删除
	int delete(int[] ids);
	//根据Id查询
	Spec getSpecById(int id);
}
