package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.BranchClass;

import java.util.List;

public interface BranchClassDao {
	public List<BranchClass> getAll();
	public BranchClass getBranchClassById(int rowId);
	public void insert(BranchClass branchClass);
	public void update(BranchClass branchClass);
	public void delete(int rowId);
}
