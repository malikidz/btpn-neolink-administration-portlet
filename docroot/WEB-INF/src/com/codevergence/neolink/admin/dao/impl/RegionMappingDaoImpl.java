package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.RegionMappingDao;
import com.codevergence.neolink.admin.model.LineOfBusiness;
import com.codevergence.neolink.admin.model.Province;
import com.codevergence.neolink.admin.model.RegionMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("regionMapping")
public class RegionMappingDaoImpl implements RegionMappingDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	public void save(RegionMapping regionMapping) {
		jdbcTemplate.update(
				"INSERT INTO Neolink_Region_Mapping (UNIT_BUSINESS_ID, REGION_ID, PROPINSI_ID) VALUES (?,?,?)",
				new Object[] { regionMapping.getLob().getUnitBusinessId(), regionMapping.getId(), regionMapping.getProvinse().getRowId() });
	}

	public void deleteRegionMapping(long provinceId, String lineOfBusinessId) {
		jdbcTemplate.update(
				"DELETE Neolink_Region_Mapping WHERE UNIT_BUSINESS_ID = ? AND PROPINSI_ID = ?",
				new Object[] { lineOfBusinessId, provinceId });
	}

	public List<RegionMapping> getRegionMapping(String unitBusinessId, long provinceId) {
			List<RegionMapping> results = new ArrayList<RegionMapping>();
			results = jdbcTemplate.query("SELECT * FROM Neolink_Region_Mapping WHERE UNIT_BUSINESS_ID = ? AND PROPINSI_ID = ?", 
					new RegionMappingMapper(), new Object[]{unitBusinessId, provinceId});
			return results;
	}
		
	private static final class RegionMappingMapper implements RowMapper<RegionMapping> {
		public RegionMapping mapRow(ResultSet rs, int arg1) throws SQLException {
			RegionMapping regionMapping  = new RegionMapping();
			regionMapping.setId(rs.getInt("id"));
			
			LineOfBusiness lob = new LineOfBusiness();
			lob.setUnitBusinessId(rs.getString("UNIT_BUSINESS_ID"));
			regionMapping.setLob(lob);
			
			regionMapping.setId(rs.getLong("REGION_ID"));
			
			Province province = new Province();
			province.setRowId(rs.getLong("PROPINSI_ID"));
			regionMapping.setProvinse(province);
			return  regionMapping;
		}
	}

}
