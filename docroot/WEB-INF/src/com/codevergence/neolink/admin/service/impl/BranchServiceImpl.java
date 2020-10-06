package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.BranchDao;
import com.codevergence.neolink.admin.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("branchService")
public class BranchServiceImpl implements BranchService{

	@Qualifier("branchDao")
	private BranchDao branchDao;
	
	public void updateUnitBusinessId(String oldBusinessId, String newBusinessId) {
		branchDao.updateUnitBusinessId(oldBusinessId, newBusinessId);
		
	}

	public void updatePropinsi(String oldPropinsi, String newPropinsi, String newPropinsiId) {
		branchDao.updatePropinsi(oldPropinsi, newPropinsi, newPropinsiId);
		
	}

	public void updateBranchClass(String oldBranchClass, String newBranchClass) {
		branchDao.updateBranchClass(oldBranchClass, newBranchClass);
		
	}

	public BranchDao getBranchDao() {
		return branchDao;
	}

	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	public void updateBranchType(String oldBranchTypeId, String newBranchTypeId) {
		branchDao.updateBranchType(oldBranchTypeId, newBranchTypeId);
		
	}

	public void updateRegion(String oldRegion, String newRegion) {
		branchDao.updateRegion(oldRegion, newRegion);
		
	}

}
