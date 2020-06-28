package com.zcg.hgshop.dao;

import java.util.List;

import com.zcg.hgshop.domain.Spec;
import com.zcg.hgshop.domain.SpecOption;

public interface SpecDao {
	//查询列表
	List<Spec> selects(Spec spec);
	//添加
	int insert(Spec spec);
	int insertOption(SpecOption specOption);
	//修改
	int update(Spec spec);
	//删除
	int delete(int[] ids);
	int deleteOption(int specId);
	//根据Id查询
	Spec getSpecById(int id);
	
}
