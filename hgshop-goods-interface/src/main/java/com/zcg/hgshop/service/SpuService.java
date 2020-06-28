package com.zcg.hgshop.service;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.domain.Spu;
import com.zcg.hgshop.domain.SpuVO;

public interface SpuService {
	int add(Spu spu);
	int update(Spu spu);
	int delete(int[] ids);
	
	PageInfo<Spu> selects(SpuVO spuVO);
	
	Spu getById(int id);
}
