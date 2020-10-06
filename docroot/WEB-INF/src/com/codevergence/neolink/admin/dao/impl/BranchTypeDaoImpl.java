package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.BranchTypeDao;
import com.codevergence.neolink.admin.model.BranchType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("branchTypeDao")
public class BranchTypeDaoImpl implements BranchTypeDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(BranchType branchType) throws DataAccessException {
		jdbcTemplate.update(
				"INSERT INTO Neolink_Master_Branch_Type (ROW_ID, BRANCH_TYPE_ID, BRANCH_TYPE_NAME, BRANCH_TYPE_MAP_MARKER, " +
				"	SHOW_ORDER, SANDI_KANTOR, COLOR_CODE, perhitungan_ami, mapview) " +
				"VALUES ((select max(row_id) from Neolink_Master_Branch_Type)+1, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] { branchType.getBranchTypeId(), branchType.getBranchTypeName(), branchType.getBranchTypeMapMarker(), 
						branchType.getShowOrder(), branchType.getSandiKantor(), branchType.getColorCode(),
						branchType.isPerhitunganAmi(), branchType.isMapView()});
		
	}

	public void update(BranchType branchType) throws DataAccessException {
		jdbcTemplate.update(
				"UPDATE Neolink_Master_Branch_Type SET BRANCH_TYPE_ID = ?, BRANCH_TYPE_NAME = ?, BRANCH_TYPE_MAP_MARKER = ?, " +
				"	SHOW_ORDER = ?, SANDI_KANTOR = ?, COLOR_CODE = ?, perhitungan_ami = ?, mapview = ? " +
				"WHERE ROW_ID = ?",
				new Object[] {  branchType.getBranchTypeId(), branchType.getBranchTypeName(), branchType.getBranchTypeMapMarker(),
						branchType.getShowOrder(), branchType.getSandiKantor(), branchType.getColorCode(),
						branchType.isPerhitunganAmi(), branchType.isMapView(), branchType.getRowId() });
	}

	
	public void delete(long id) throws DataAccessException {
		this.jdbcTemplate.update("DELETE FROM Neolink_Master_Branch_Type where row_id = ?", id);
		
	}

	public BranchType withId(long id) throws DataAccessException {
		List<BranchType> results = new ArrayList<BranchType>();
		results  = jdbcTemplate.query("SELECT * FROM Neolink_Master_Branch_Type WHERE row_id = ?",
				new Object[] { id }, new BranchTypeMapper());
		BranchType result = DataAccessUtils.singleResult(results);
		return result;
		
	}

	public List<BranchType> getAll() throws DataAccessException {
		List<BranchType> results = new ArrayList<BranchType>();
		results  = jdbcTemplate.query("SELECT * FROM Neolink_Master_Branch_Type order by SHOW_ORDER",
				new BranchTypeMapper());
		return results;
	}
	
	private static final class BranchTypeMapper implements RowMapper<BranchType> {
		public BranchType mapRow(ResultSet rs, int arg1) throws SQLException {
			BranchType branchType = new BranchType();
			branchType.setBranchTypeId(rs.getString("BRANCH_TYPE_ID"));
			branchType.setBranchTypeMapMarker(rs.getString("BRANCH_TYPE_MAP_MARKER"));
			branchType.setBranchTypeName(rs.getString("BRANCH_TYPE_NAME"));
			branchType.setColorCode(rs.getString("COLOR_CODE"));
			branchType.setRowId(rs.getLong("ROW_ID"));
			branchType.setSandiKantor(rs.getString("SANDI_KANTOR"));
			branchType.setShowOrder(rs.getInt("SHOW_ORDER"));
			branchType.setPerhitunganAmi(rs.getBoolean("perhitungan_ami"));
			branchType.setMapView(rs.getBoolean("mapview"));
			return branchType;
		}
	}

	public Hashtable<Long, BranchType> getAllAsHash()
			throws DataAccessException {
		List<BranchType> results = new ArrayList<BranchType>();
		Hashtable<Long, BranchType> resultHash = new Hashtable<Long, BranchType>();
		
		results  = this.getAll();
		for (int i = 0; i < results.size(); i++) {
			BranchType branchType  = results.get(i);
			resultHash.put(new Long(branchType.getRowId()), branchType);
		}
		return resultHash;
	}

	public List<BranchType> getBranchTypeWithPerhitunganAmi() {
		List<BranchType> results = new ArrayList<BranchType>();
		results  = jdbcTemplate.query(
				"SELECT * FROM Neolink_Master_Branch_Type " +
				"WHERE perhitungan_ami = 1 " +
				"ORDER BY SHOW_ORDER",
				new BranchTypeMapper());
		return results;
	}

}
