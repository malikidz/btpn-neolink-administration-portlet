package com.codevergence.neolink.admin.service;

public interface BranchService {
	public void updateUnitBusinessId(String oldBusinessId, String newBusinessId);
	public void updatePropinsi(String oldPropinsi, String newPropinsi, String newPropinsiId);
	public void updateBranchClass(String oldBranchClass, String newBranchClass);
	public void updateBranchType(String oldBranchTypeId, String newBranchTypeId);
	public void updateRegion(String oldRegion, String newRegion);
}
