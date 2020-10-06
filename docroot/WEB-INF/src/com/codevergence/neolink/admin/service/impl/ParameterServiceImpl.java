package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.ParameterDao;
import com.codevergence.neolink.admin.model.Parameter;
import com.codevergence.neolink.admin.service.ParameterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("parameterService")
public class ParameterServiceImpl implements ParameterService {

	@Qualifier("parameterDao")
	private ParameterDao parameterDao;

	public ParameterDao getParameterDao() {
		return parameterDao;
	}

	@Autowired
	public void setParameterDao(ParameterDao parameterDao) {
		this.parameterDao = parameterDao;
	}

	public String getValue(String param) {
		return parameterDao.getValue(param);
	}

	public List<Parameter> getParameters() {
		return parameterDao.getParameters();
	}

	public void save(Parameter parameter) {
		parameterDao.save(parameter);
	}

	public void excute(String sql) {
		parameterDao.execute(sql);
	}

}
