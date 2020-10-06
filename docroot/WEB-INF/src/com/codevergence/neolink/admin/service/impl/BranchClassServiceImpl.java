package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.BranchClassDao;
import com.codevergence.neolink.admin.model.BranchClass;
import com.codevergence.neolink.admin.service.BranchClassService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("branchClassService")
public class BranchClassServiceImpl implements BranchClassService {

	@Qualifier("branchClassDao")
	private BranchClassDao branchClassDao ;

	public BranchClassDao getBranchClassDao() {
		return branchClassDao;
	}

	@Autowired
	public void setBranchClassDao(BranchClassDao branchClassDao) {
		this.branchClassDao = branchClassDao;
	}

	public List<BranchClass> getAll() {
		return branchClassDao.getAll();
	}

	public BranchClass getBranchClassById(int rowId) {
		return branchClassDao.getBranchClassById(rowId);
	}

	public void insert(BranchClass branchClass) {
		branchClassDao.insert(branchClass);
	}

	public void update(BranchClass branchClass) {
		branchClassDao.update(branchClass);
	}

	public void delete(int rowId) {
		branchClassDao.delete(rowId);
	}

}
