package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.RegionMapping;

import java.util.List;

public interface RegionMappingService {
	public void save(RegionMapping regionMapping);
	public void deleteRegionMapping(long provinceId, String lineOfBusinessId);
	
	public List<RegionMapping> getRegionMapping(String unitBusinessId, long provinceId);
}
