package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.ProvinceDao;
import com.codevergence.neolink.admin.model.Province;
import com.codevergence.neolink.admin.service.ProvinceService;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService{

	@Qualifier("branchTypeDao")
	private ProvinceDao provinceDao ;
	
	public void insert(Province province) {
		provinceDao.insert(province);
		
	}

	public void update(Province province) {
		provinceDao.update(province);
		
	}

	public void delete(long rowId) {
		provinceDao.delete(rowId);
		
	}

	public Province withId(long id) {
		return provinceDao.withId(id);
	}

	public List<Province> getAll() {
		return provinceDao.getAll();
	}

	public Hashtable<Long, Province> getAllAsHash() {
		return provinceDao.getAllAsHash();
	}

	public ProvinceDao getProvinceDao() {
		return provinceDao;
	}

	@Autowired
	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}

}
