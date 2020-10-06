package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.InvestStandardBI;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface InvestStandardBIDao {
	public void insert(InvestStandardBI investStandardBI) throws DataAccessException;
	public void update(InvestStandardBI investStandardBI) throws DataAccessException;
	public void delete(long rowId) throws DataAccessException;
	
	public InvestStandardBI withId(long id) throws DataAccessException;
	public List<InvestStandardBI> getAll() throws DataAccessException;
}
