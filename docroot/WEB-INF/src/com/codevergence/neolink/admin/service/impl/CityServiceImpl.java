package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.CityDao;
import com.codevergence.neolink.admin.model.City;
import com.codevergence.neolink.admin.service.CityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("cityService")
public class CityServiceImpl implements CityService {

	@Qualifier("cityDao")
	private CityDao cityDao;
	
	public List<City> getAll() throws DataAccessException {
		return cityDao.getAll();
	}

	public List<City> getAllForPropinsi(String propinsiName) throws DataAccessException {
		return cityDao.getAllForPropinsi(propinsiName);
	}

	public City withId(int cityId) throws DataAccessException {
		return cityDao.withId(cityId);
	}

	public void delete(int cityId) throws DataAccessException {
		cityDao.delete(cityId);
		
	}

	public void insert(City city) throws DataAccessException {
		cityDao.insert(city);
		
	}

	public void update(City city) throws DataAccessException {
		cityDao.update(city);
		
	}

	public CityDao getCityDao() {
		return cityDao;
	}

	@Autowired
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

}
