package com.zcg.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.dao.SpuDao;
import com.zcg.hgshop.domain.Spu;
import com.zcg.hgshop.domain.SpuVO;
import com.zcg.hgshop.service.SpuService;
@Service
public class SpuServiceImpl implements SpuService{
	@Autowired
	SpuDao spuDao;
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	
	@Override
	public int add(Spu spu) {
		int i = spuDao.add(spu);
		if(i>0) {
			int spuId = spu.getId();
			Spu spu2 = spuDao.getById(spuId);
			String spujson = JSON.toJSONString(spu2);
			kafkaTemplate.send("hgspu", "addspu", spujson);
		}
		return i;
	}

	@Override
	public int update(Spu spu) {
		int i = spuDao.update(spu);
		return i;
	}

	@Override
	public int delete(int[] ids) {
		int i = spuDao.delete(ids);
		if(i>0) {
			String delIdsStr = JSON.toJSONString(ids);
			kafkaTemplate.send("hgspu", "delspu",delIdsStr);
		}
		return i;
	}

	@Override
	public PageInfo<Spu> selects(SpuVO spuVO) {
		PageHelper.startPage(spuVO.getPageNum(), spuVO.getPageSize());
		List<Spu> list = spuDao.selects(spuVO);
		return new PageInfo<Spu>(list);
	}

	@Override
	public Spu getById(int id) {
		Spu spu = spuDao.getById(id);
		return spu;
	}

}
