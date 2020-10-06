package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.ZonaBI;

import java.util.List;

public interface ZonaBIService {
	public List<ZonaBI> getAll();
	public ZonaBI getById(long id);
	public void insert(ZonaBI zonabi);
	public void update(ZonaBI zonabi);
	public void delete(long id);
}
