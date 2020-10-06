package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.LineOfBusiness;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface LineOfBusinessDao {
	public List<LineOfBusiness> getAll() throws DataAccessException;
	public LineOfBusiness getById(long id) throws DataAccessException;
	public void insert(LineOfBusiness lob) throws DataAccessException;
	public void update(LineOfBusiness lob) throws DataAccessException;
	public void delete(long id) throws DataAccessException;
	
	public LineOfBusiness getByUnitBusinessId(String unitBusinessId) throws DataAccessException;
}
