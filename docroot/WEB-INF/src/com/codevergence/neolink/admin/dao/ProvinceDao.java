package com.codevergence.neolink.admin.dao;


import com.codevergence.neolink.admin.model.Province;

import java.util.Hashtable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ProvinceDao {

	public void insert(Province province) throws DataAccessException;
	public void update(Province province) throws DataAccessException;
	public void delete(long rowId) throws DataAccessException;
	
	public Province withId(long id) throws DataAccessException;
	public List<Province> getAll() throws DataAccessException;
	public Hashtable<Long, Province> getAllAsHash() throws DataAccessException;
	
}
