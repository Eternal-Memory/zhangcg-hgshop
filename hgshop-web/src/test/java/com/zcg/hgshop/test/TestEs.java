package com.zcg.hgshop.test;

import static org.junit.Assert.*;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.controller.ElSearchUtil;
import com.zcg.hgshop.domain.EsSpu;
import com.zcg.hgshop.domain.Spu;
import com.zcg.hgshop.domain.SpuVO;
import com.zcg.hgshop.service.SpuService;
@ContextConfiguration("classpath:test-consumer.xml")
@RunWith(SpringRunner.class)
public class TestEs {
	@Reference
	SpuService spuService;
	
	@Autowired
	ElSearchUtil<EsSpu> elSearchUtil;
	
	@Test
	public void test() {
		SpuVO spuVO = new SpuVO();
		spuVO.setPageSize(300);
		PageInfo<Spu> info = spuService.selects(spuVO);
		info.getList().forEach(spu->{
			EsSpu esSpu = new EsSpu(spu);
			elSearchUtil.saveObject(esSpu.getId()+"", esSpu);
		});
		
	}

}
