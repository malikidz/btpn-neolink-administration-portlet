package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.KabupatenKotaDao;
import com.codevergence.neolink.admin.model.KabupatenKota;
import com.codevergence.neolink.admin.service.KabupatenKotaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("kabupatenKotaService")
public class KabupatenKotaServiceImpl implements KabupatenKotaService {
	
	@Qualifier("kabupatenKotaDao")
	private KabupatenKotaDao kabupatenKotaDao;
	
	public KabupatenKotaDao getKabupatenKotaDao() {
		return kabupatenKotaDao;
	}
	
	@Autowired
	public void setKabupatenKotaDao(KabupatenKotaDao kabupatenKotaDao) {
		this.kabupatenKotaDao = kabupatenKotaDao;
	}

	public List<KabupatenKota> getAll() {
		// TODO Auto-generated method stub
		return this.kabupatenKotaDao.getAll();
	}

	public KabupatenKota getById(int id) {
		// TODO Auto-generated method stub
		return this.kabupatenKotaDao.getById(id);
	}

	public void insert(KabupatenKota kabupatenKota) {
		this.kabupatenKotaDao.insert(kabupatenKota);
	}

	public void update(KabupatenKota kabupatenKota) {
		this.kabupatenKotaDao.update(kabupatenKota);
	}

	public void delete(int id) {
		this.kabupatenKotaDao.delete(id);
	}

	public void active(int id, boolean isActive) {
		this.kabupatenKotaDao.active(id, isActive);
	}

	public void updateNeolinkKabupaten(String oldKabupatenKota,
			String newKabupatenKota) {
		this.kabupatenKotaDao.updateNeolinkKabupaten(oldKabupatenKota, newKabupatenKota);
		
	}

	public KabupatenKota withIdAndName(String kabupatenKode, String name) {
		return this.kabupatenKotaDao.withIdAndName(kabupatenKode, name);
	}

}
