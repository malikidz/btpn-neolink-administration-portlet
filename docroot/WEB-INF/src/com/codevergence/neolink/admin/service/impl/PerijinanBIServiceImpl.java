package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.PerijinanBIDao;
import com.codevergence.neolink.admin.model.Branch;
import com.codevergence.neolink.admin.model.PerijinanBI;
import com.codevergence.neolink.admin.service.PerijinanBIService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("perijinanBIService")
public class PerijinanBIServiceImpl implements PerijinanBIService{
	
	@Qualifier("perijinanBIDao")
	private PerijinanBIDao perijinanBIDao;

	public PerijinanBIDao getPerijinanBIDao() {
		return perijinanBIDao;
	}
	
	@Autowired
	public void setPerijinanBIDao(PerijinanBIDao perijinanBIDao) {
		this.perijinanBIDao = perijinanBIDao;
	}

	public void insert(PerijinanBI perijinanBI) {
		this.perijinanBIDao.insert(perijinanBI);
	}
	
	public void update(PerijinanBI perijinanBI) {
		this.perijinanBIDao.update(perijinanBI);
	}

	public List<PerijinanBI> getAll(boolean isActive, boolean isCompleteInformation, String name, String branchName) {
		return this.perijinanBIDao.getAll(isActive, isCompleteInformation, name, branchName);
	}

	public PerijinanBI getById(long id) {
		return this.perijinanBIDao.getById(id);
	}

	public void delete(long id) {
		this.perijinanBIDao.delete(id);
	}
	
	public List<Branch> getBranchHasDocument() {
		return this.perijinanBIDao.getBranchHasDocument();
	}

	public List<String> getNameHasDocument() {
		return this.perijinanBIDao.getNameHasDocument();
	}

	public void toggleRecap(boolean isRecap, long id) {
		this.perijinanBIDao.toggleRecap(isRecap, id);
	}

	public void toggleRecapEfective(boolean isRecapEfective, long id) {
		this.perijinanBIDao.toggleRecapEfective(isRecapEfective, id);
	}

	public void updateOpeningDateMasterBranch(Date openingDate, long dokumenBiId) {
		this.perijinanBIDao.updateOpeningDateMasterBranch(openingDate, dokumenBiId);
		
	}
}
