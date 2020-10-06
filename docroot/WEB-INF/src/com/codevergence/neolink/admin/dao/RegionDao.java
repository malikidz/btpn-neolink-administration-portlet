package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.Region;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface RegionDao {

	public List<Region> getAll() throws DataAccessException;
	public void insert(Region region) throws DataAccessException;
	public void update(Region region) throws DataAccessException;
	public void delete(long regionId) throws DataAccessException;
	public Region withId(long id) throws DataAccessException;
	
	//new region
	public List<Region> getForUnitBusiness(long unitBusinessId);
	public List<Region> getForProvince(long provinceId);
	public void insertForMapping(Region region);
	public void updateForMapping(Region region);
	public void deleteForMapping(long mappingId);
	public void deleteForMappingForLOBId(long lobId);
}
