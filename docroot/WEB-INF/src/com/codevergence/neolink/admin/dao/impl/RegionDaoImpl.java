package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.RegionDao;
import com.codevergence.neolink.admin.model.Region;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("regionDao")
public class RegionDaoImpl implements RegionDao{
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static String SQL_ALL = "select * from Neolink_Master_Region";
	public List<Region> getAll() throws DataAccessException{
		List<Region> results = new ArrayList<Region>();
		results = jdbcTemplate.query(SQL_ALL, new RegionMapper());
		return results;
	}
	
	private static final class RegionMapper implements RowMapper<Region> {
		public Region mapRow(ResultSet rs, int arg1) throws SQLException {
			Region region  = new Region();
			region.setId(rs.getInt("id"));
			region.setName(rs.getString("name"));
			region.setRegionCode(rs.getString("region_code"));
			return  region;
		}
	}

	private static String SQL_WITH_ID = "select * from Neolink_Master_Region WHERE id = ?";
	public Region withId(long id) throws DataAccessException {
		Region region = jdbcTemplate.queryForObject(
				SQL_WITH_ID, 
				new Object[]{ id },
				new RegionMapper());
		return region;
	}

	private static String SQL_INSERT = "insert into Neolink_Master_Region (region_code, name) values (?, ?)";
	public void insert(Region region) throws DataAccessException {
		jdbcTemplate.update(
				SQL_INSERT,
				new Object[] { region.getRegionCode(), region.getName() });
	}

	private static String SQL_UPDATE = "update Neolink_Master_Region set name = ?, region_code = ? where id = ?";
	public void update(Region region) throws DataAccessException {
		jdbcTemplate.update(
				SQL_UPDATE,
				new Object[] { region.getName(), region.getRegionCode(), region.getId() });
		
	}

	private static String SQL_DELETE = "delete from Neolink_Master_Region where id = ?";
	public void delete(long regionId) throws DataAccessException {
		this.jdbcTemplate.update(SQL_DELETE, regionId);
	}

	private static String REGION_MAPPING_GET_FOR_UNITBUSINESS_SQL = "select * from( "+
"SELECT "+
"a.id as mapper_id,  "+
"a.propinsi_id as propinsi_id,  "+
"b.PROPINSI_NAME,  "+
"c.ROW_ID as unit_business_id,  "+
"c.UNIT_BUSINESS_NAME,  "+
"a.region_id, "+
"d.name as region_name "+
"  FROM Neolink_Propinsi_Unit_Business_Region_Map a "+
" join Neolink_Master_Propinsi b on b.ROW_ID = a.propinsi_id "+
" join Neolink_Master_Unit_Business c on c.ROW_ID = a.unit_business_id "+
" join Neolink_Master_Region d on d.id = a.region_id "+
" where C.ROW_ID = ? "+
"union "+
"select  "+
"-1,  "+
"b.ROW_ID,  "+
"b.PROPINSI_NAME as PROPINSI_NAME,  "+
"-1,  "+
"'', "+
"-1,  "+
"'' "+
"from Neolink_Master_Propinsi b where  "+
"b.PROPINSI_NAME not in ( "+
"	SELECT b.PROPINSI_NAME "+
"  FROM Neolink_Propinsi_Unit_Business_Region_Map a "+
" join Neolink_Master_Propinsi b on b.ROW_ID = a.propinsi_id "+
" join Neolink_Master_Unit_Business c on c.ROW_ID = a.unit_business_id "+
" join Neolink_Master_Region d on d.id = a.region_id "+
" where C.ROW_ID = ? "+
"	) "+
") as temp "+
"order by PROPINSI_NAME ";
	public List<Region> getForUnitBusiness(long unitBusinessId) {
		List<Region> regions = jdbcTemplate.query(
				REGION_MAPPING_GET_FOR_UNITBUSINESS_SQL, 
				new Object[]{ unitBusinessId, unitBusinessId},
				new NewRegionMapper());
		return regions;
	}

	/*
	private static String REGION_MAPPING_GET_PROVINCE_SQL ="select c.id as region_id, b.ROW_ID as propinsi_id, c.name as region_name, b.PROPINSI_NAME as PROPINSI_NAME from "+
	"Neolink_Propinsi_Unit_Business_Region_Map a "+
	"join Neolink_Master_Propinsi b on b.ROW_ID = a.propinsi_id "+
	"join Neolink_Master_Region c on c.id = a.region_id";
	*/
	
	public List<Region> getForProvince(long provinceId) {
		List<Region> regions = jdbcTemplate.query(
				REGION_MAPPING_GET_FOR_UNITBUSINESS_SQL, 
				new Object[]{ provinceId },
				new NewRegionMapper());
		return regions;
	}

	private static String REGION_MAPPING_INSERT_SQL = "INSERT INTO Neolink_Propinsi_Unit_Business_Region_Map "+
"           (propinsi_id "+
"           ,unit_business_id "+
"           ,region_id) "+
"     VALUES "+
"           (? "+
"           ,? "+
"           ,?)";
	public void insertForMapping(Region region) {
		jdbcTemplate.update(
				REGION_MAPPING_INSERT_SQL,
				new Object[] { region.getProvinceId(), region.getUnitBusinessId(), region.getId() });
		
	}

	private static String REGION_MAPPING_UPDATE_SQL = "UPDATE Neolink_Propinsi_Unit_Business_Region_Map "+
"   SET  propinsi_id  = ? "+
"      , unit_business_id  =? "+
"      , region_id  = ? "+
" WHERE id = ?";
	public void updateForMapping(Region region) {
		jdbcTemplate.update(
				REGION_MAPPING_UPDATE_SQL,
				new Object[] { region.getProvinceId(), region.getUnitBusinessId(), region.getId(), region.getMappingId()});
		
	}

	private static String REGION_MAPPING_DELETE_SQL = "DELETE FROM Neolink_Propinsi_Unit_Business_Region_Map WHERE id = ?";
	public void deleteForMapping(long mappingId) {
		jdbcTemplate.update(
				REGION_MAPPING_DELETE_SQL,
				new Object[] {mappingId});
	}
	
	private static final class NewRegionMapper implements RowMapper<Region> {
		public Region mapRow(ResultSet rs, int arg1) throws SQLException {
			Region region  = new Region();
			region.setMappingId(rs.getLong("mapper_id"));
			region.setProvinceId(rs.getLong("propinsi_id"));
			region.setProvinceName(rs.getString("PROPINSI_NAME"));
			region.setUnitBusinessId(rs.getLong("unit_business_id"));
			region.setUnitBusinessName(rs.getString("UNIT_BUSINESS_NAME"));
			region.setId(rs.getLong("region_id"));
			region.setName(rs.getString("region_name"));
			return  region;
		}
	}

	private static String REGION_MAPPING_DELETE_WITH_LOB_ID_SQL = "DELETE FROM Neolink_Propinsi_Unit_Business_Region_Map WHERE unit_business_id = ?";
	public void deleteForMappingForLOBId(long lobId) {
		jdbcTemplate.update(
				REGION_MAPPING_DELETE_WITH_LOB_ID_SQL,
				new Object[] {lobId});
		
	}
	
	
}