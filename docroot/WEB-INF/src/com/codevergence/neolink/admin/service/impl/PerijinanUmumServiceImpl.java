package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.PerijinanUmumDao;
import com.codevergence.neolink.admin.model.Branch;
import com.codevergence.neolink.admin.model.PerijinanUmum;
import com.codevergence.neolink.admin.service.PerijinanUmumService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("perijinanUmumService")
public class PerijinanUmumServiceImpl implements PerijinanUmumService{
	
	@Qualifier("perijinanUmumDao")
	private PerijinanUmumDao perijinanUmumDao;

	public PerijinanUmumDao getPerijinanUmumDao() {
		return perijinanUmumDao;
	}
	
	@Autowired
	public void setPerijinanUmumDao(PerijinanUmumDao perijinanUmumDao) {
		this.perijinanUmumDao = perijinanUmumDao;
	}

	public void insert(PerijinanUmum perijinanUmum) {
		this.perijinanUmumDao.insert(perijinanUmum);
	}
	
	public void update(PerijinanUmum perijinanUmum) {
		this.perijinanUmumDao.update(perijinanUmum);
	}

	public List<PerijinanUmum> getAll(boolean isActive, boolean isCompleteInformation, String name, String branchName) {
		return this.perijinanUmumDao.getAll(isActive, isCompleteInformation, name, branchName);
	}

	public PerijinanUmum getById(long id) {
		return this.perijinanUmumDao.getById(id);
	}

	public void delete(long id) {
		this.perijinanUmumDao.delete(id);
	}

	public List<Branch> getBranchHasDocument() {
		return this.perijinanUmumDao.getBranchHasDocument();
	}

	public List<String> getNameHasDocument() {
		return this.perijinanUmumDao.getNameHasDocument();
	}
	
	
}
