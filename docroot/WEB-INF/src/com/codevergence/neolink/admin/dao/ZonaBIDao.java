package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.ZonaBI;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ZonaBIDao {
	public List<ZonaBI> getAll() throws DataAccessException;
	public ZonaBI getById(long id) throws DataAccessException;
	public void insert(ZonaBI zonabi) throws DataAccessException;
	public void update(ZonaBI zonabi) throws DataAccessException;
	public void delete(long id) throws DataAccessException;
}
