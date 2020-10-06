package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.RegionMapping;

import java.util.List;

public interface RegionMappingDao {
	public void save(RegionMapping regionMapping);
	public void deleteRegionMapping(long provinceId, String lineOfBusinessId);
	
	public List<RegionMapping> getRegionMapping(String unitBusinessId, long provinceId);
}
