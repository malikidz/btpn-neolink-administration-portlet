package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.LineOfBusiness;

import java.util.List;

public interface LineOfBusinessService {
	public List<LineOfBusiness> getAll();
	public LineOfBusiness getById(long id);
	public void insert(LineOfBusiness lob);
	public void update(LineOfBusiness lob);
	public void delete(long id);
	
	public LineOfBusiness getByUnitBusinessId(String unitBusinessId);
}
