package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.BranchTypeDao;
import com.codevergence.neolink.admin.model.BranchType;
import com.codevergence.neolink.admin.service.BranchTypeService;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("branchTypeService")
public class BranchTypeServiceImpl implements BranchTypeService {

	@Qualifier("branchTypeDao")
	private BranchTypeDao branchTypeDao ;
	
	public void insert(BranchType branchType) {
		branchTypeDao.insert(branchType);
	}

	public void update(BranchType branchType) {
		branchTypeDao.update(branchType);
	}

	public void delete(long rowId) {
		branchTypeDao.delete(rowId);
	}

	public BranchType withId(long id) {
		return branchTypeDao.withId(id);
	}

	public List<BranchType> getAll() {
		return branchTypeDao.getAll();
	}

	public BranchTypeDao getBranchTypeDao() {
		return branchTypeDao;
	}

	@Autowired
	public void setBranchTypeDao(BranchTypeDao branchTypeDao) {
		this.branchTypeDao = branchTypeDao;
	}

	public Hashtable<Long, BranchType> getAllAsHash() {
		return branchTypeDao.getAllAsHash();
	}

	public List<BranchType> getBranchTypeWithPerhitunganAmi() {
		return branchTypeDao.getBranchTypeWithPerhitunganAmi();
	}

}
