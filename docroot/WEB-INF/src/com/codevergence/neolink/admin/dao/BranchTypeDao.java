package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.BranchType;

import java.util.Hashtable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface BranchTypeDao {

	public void insert(BranchType branchType) throws DataAccessException;
	public void update(BranchType branchType) throws DataAccessException;
	public void delete(long rowId) throws DataAccessException;
	
	public BranchType withId(long id) throws DataAccessException;
	
	public List<BranchType> getAll() throws DataAccessException;
	public List<BranchType> getBranchTypeWithPerhitunganAmi();

	public Hashtable<Long, BranchType> getAllAsHash() throws DataAccessException;
}
