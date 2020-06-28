package com.zcg.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcg.hgshop.dao.SpecDao;
import com.zcg.hgshop.domain.Spec;
import com.zcg.hgshop.domain.SpecOption;
import com.zcg.hgshop.service.SpecService;
@Service
public class SpecServiceImpl implements SpecService{
	@Autowired
	SpecDao specDao;
	
	@Override
	public PageInfo<Spec> selects(Spec spec, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Spec> list = specDao.selects(spec);
		return new PageInfo<Spec>(list);
	}

	@Override
	public int insert(Spec spec) {
		// TODO Auto-generated method stub
		int i=0;
		try {
			specDao.insert(spec);
			List<SpecOption> list = spec.getOptions();
			for (SpecOption specOption : list) {
				specOption.setSpecId(spec.getId());
				i=specDao.insertOption(specOption);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int update(Spec spec) {
		// TODO Auto-generated method stub
		int i=0;
		try {
			specDao.deleteOption(spec.getId());
			specDao.update(spec);
			List<SpecOption> list = spec.getOptions();
			for (SpecOption specOption : list) {
				specOption.setSpecId(spec.getId());
				i=specDao.insertOption(specOption);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int delete(int[] ids) {
		// TODO Auto-generated method stub
		int i =0;
		try {
			for (int id: ids) {
				specDao.deleteOption(id);
			}
			i = specDao.delete(ids);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}

	@Override
	public Spec getSpecById(int id) {
		// TODO Auto-generated method stub
		Spec spec = specDao.getSpecById(id);
		return spec;
	}

}
