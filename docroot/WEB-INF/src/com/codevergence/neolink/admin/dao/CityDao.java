package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.City;

import java.util.List;

public interface CityDao {

	public List<City> getAll();
	public List<City> getAllForPropinsi(String propinsiName);
	public City withId(int cityId);
	public void delete(int cityId);
	public void insert(City city);
	public void update(City city);
}
