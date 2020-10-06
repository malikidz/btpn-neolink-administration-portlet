package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.Province;

import java.util.Hashtable;
import java.util.List;

public interface ProvinceService {

	public void insert(Province province);
	public void update(Province province);
	public void delete(long rowId);
	
	public Province withId(long id);
	public List<Province> getAll();
	public Hashtable<Long, Province> getAllAsHash();
	
}
