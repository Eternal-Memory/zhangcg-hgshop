package com.zcg.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.dao.SkuDao;
import com.zcg.hgshop.domain.Sku;
import com.zcg.hgshop.domain.SkuVO;
import com.zcg.hgshop.domain.SpecOption;
import com.zcg.hgshop.service.SkuService;
@Service
public class SkuServiceImpl implements SkuService{
	@Autowired
	SkuDao skuDao;
	
	@Override
	public int insert(Sku sku) {
		// TODO Auto-generated method stub
		int i =0;
		try {
			skuDao.insert(sku);
			List<SpecOption> options = sku.getOptions();
			for (SpecOption SpecOption : options) {
				System.out.println("++++++++");
				i=skuDao.insertSpecOption(sku.getId(),SpecOption);
				System.out.println("==========");
			}
			return i;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int update(Sku sku) {
		// TODO Auto-generated method stub
		int i =0;
		skuDao.deleteSpecOption(sku.getId());
		skuDao.update(sku);
		List<SpecOption> options = sku.getOptions();
		for (SpecOption SpecOption : options) {
			i=skuDao.insertSpecOption(sku.getId(),SpecOption);
		}
		
		return i;
	}

	@Override
	public int delete(int[] ids) {
		// TODO Auto-generated method stub
		int i=0;
		try {
			for (int id : ids) {
				skuDao.deleteSpecOption(id);
			}
			i = skuDao.delete(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public PageInfo<Sku> selects(SkuVO skuVO) {
		PageHelper.startPage(skuVO.getPageNum(), skuVO.getPageSize());
		List<Sku> list = skuDao.selects(skuVO);
		return new PageInfo<Sku>(list);
	}

	@Override
	public Sku getById(int id) {
		Sku sku = skuDao.getById(id);
		return sku;
	}

}
