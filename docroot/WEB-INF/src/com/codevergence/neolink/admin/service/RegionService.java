package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.Region;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface RegionService {

	public List<Region> getAll() throws DataAccessException;
	public void insert(Region region) throws DataAccessException;
	public void update(Region region) throws DataAccessException;;
	public void delete(long regionId) throws DataAccessException;
	public Region withId(long id) throws DataAccessException;
	
	//new region
	public List<Region> getForUnitBusiness(long unitBusinessId) throws DataAccessException;
	public List<Region> getForProvince(long provinceId) throws DataAccessException;
	public void insertForMapping(Region region) throws DataAccessException;
	public void updateForMapping(Region region) throws DataAccessException;
	public void deleteForMapping(long mappingId) throws DataAccessException;
	public void deleteForMappingForLOBId(long lobId) throws DataAccessException;
}
