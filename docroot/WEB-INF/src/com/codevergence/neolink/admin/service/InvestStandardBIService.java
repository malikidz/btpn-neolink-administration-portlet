package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.InvestStandardBI;

import java.util.List;

public interface InvestStandardBIService {

	public List<InvestStandardBI> getAll();
	public InvestStandardBI withId(long id);
	
	public void insert(InvestStandardBI investStandardBI);
	public void update(InvestStandardBI investStandardBI);
	public void delete(long id);
}
