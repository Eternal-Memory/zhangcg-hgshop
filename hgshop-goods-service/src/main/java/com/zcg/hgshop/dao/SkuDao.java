package com.zcg.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zcg.hgshop.domain.Sku;
import com.zcg.hgshop.domain.SkuVO;
import com.zcg.hgshop.domain.SpecOption;

public interface SkuDao {
	int insert(Sku sku);
	int insertSpecOption(@Param("skuId")int id,@Param("opt")SpecOption specOption);
	
	int update(Sku sku);
	int delete(int[] ids);
	int deleteSpecOption(int id);
	
	List<Sku> selects(SkuVO skuVO);
	
	Sku getById(int id);
}
