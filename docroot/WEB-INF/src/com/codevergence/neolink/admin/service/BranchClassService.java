package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.BranchClass;

import java.util.List;

public interface BranchClassService {
	public List<BranchClass> getAll();
	public BranchClass getBranchClassById(int rowId);
	public void insert(BranchClass branchClass);
	public void update(BranchClass branchClass);
	public void delete(int rowId);
}
