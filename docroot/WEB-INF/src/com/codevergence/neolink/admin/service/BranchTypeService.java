package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.BranchType;

import java.util.Hashtable;
import java.util.List;

public interface BranchTypeService {
	public void insert(BranchType branchType) ;
	public void update(BranchType branchType) ;
	public void delete(long rowId) ;
	
	public BranchType withId(long id) ;

	public List<BranchType> getAll() ;
	public List<BranchType> getBranchTypeWithPerhitunganAmi();

	public Hashtable<Long, BranchType> getAllAsHash() ;
}
