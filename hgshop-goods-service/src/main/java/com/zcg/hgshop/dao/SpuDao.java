package com.zcg.hgshop.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.Spu;
import com.zcg.hgshop.domain.SpuVO;

public interface SpuDao {
	int add(Spu spu);
	int update(Spu spu);
	int delete(int[] ids);
	
	List<Spu> selects(SpuVO spuVO);
	
	Spu getById(int id);
}
