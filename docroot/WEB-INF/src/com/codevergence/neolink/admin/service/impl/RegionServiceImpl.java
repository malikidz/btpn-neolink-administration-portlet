package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.RegionDao;
import com.codevergence.neolink.admin.model.Region;
import com.codevergence.neolink.admin.service.RegionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("regionService")
public class RegionServiceImpl implements RegionService {

	@Qualifier("regionDao")
	private RegionDao regionDao;
	
	public List<Region> getAll() throws DataAccessException {
		return regionDao.getAll();
	}

	public RegionDao getRegionDao() {
		return regionDao;
	}

	@Autowired
	public void setRegionDao(RegionDao regionDao) {
		this.regionDao = regionDao;
	}

	public Region withId(long id) throws DataAccessException {
		return regionDao.withId(id);
	}


	public void insert(Region region) throws DataAccessException {
		regionDao.insert(region);
		
	}

	public void update(Region region) throws DataAccessException {
		regionDao.update(region);
		
	}




	public void delete(long regionId) throws DataAccessException {
		regionDao.delete(regionId);
	}

	public List<Region> getForUnitBusiness(long unitBusinessId)
			throws DataAccessException {
		
		return regionDao.getForUnitBusiness(unitBusinessId);
	}

	public List<Region> getForProvince(long provinceId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return regionDao.getForProvince(provinceId);
	}

	public void insertForMapping(Region region) throws DataAccessException {
		regionDao.insertForMapping(region);
		
	}

	public void updateForMapping(Region region) throws DataAccessException {
		regionDao.updateForMapping(region);
		
	}

	public void deleteForMapping(long mappingId) throws DataAccessException {
		regionDao.deleteForMapping(mappingId);
		
	}

	public void deleteForMappingForLOBId(long lobId) throws DataAccessException {
		regionDao.deleteForMappingForLOBId(lobId);
		
	}

}
