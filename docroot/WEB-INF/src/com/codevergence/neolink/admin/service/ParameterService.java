package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.Parameter;

import java.util.List;

public interface ParameterService {
	public String getValue(String param);
	public List<Parameter> getParameters();
	public void save(Parameter parameter);
	public void excute(String sql);
}
