package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.RegionMappingDao;
import com.codevergence.neolink.admin.model.RegionMapping;
import com.codevergence.neolink.admin.service.RegionMappingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("regionMappingService")
public class RegionMappingServiceImpl implements RegionMappingService{
	
	@Qualifier("regionMappingDao")
	private RegionMappingDao regionMappingDao;
	
	public RegionMappingDao getRegionMappingDao() {
		return regionMappingDao;
	}
	
	@Autowired
	public void setRegionMappingDao(RegionMappingDao regionMappingDao) {
		this.regionMappingDao = regionMappingDao;
	}

	public void save(RegionMapping regionMapping) {
		this.regionMappingDao.save(regionMapping);
	}

	public void deleteRegionMapping(long provinceId, String lineOfBusinessId) {
		this.regionMappingDao.deleteRegionMapping(provinceId, lineOfBusinessId);		
	}

	public List<RegionMapping> getRegionMapping(String unitBusinessId,
			long provinceId) {
		return this.regionMappingDao.getRegionMapping(unitBusinessId, provinceId);
	}

}
