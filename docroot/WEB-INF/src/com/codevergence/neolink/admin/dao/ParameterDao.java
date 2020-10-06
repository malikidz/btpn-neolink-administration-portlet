package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.Parameter;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ParameterDao {
	public String getValue(String param) throws DataAccessException;
	public List<Parameter> getParameters() throws DataAccessException;
	public void save(Parameter parameter) throws DataAccessException;
	public void execute(String sql) throws DataAccessException;
}
