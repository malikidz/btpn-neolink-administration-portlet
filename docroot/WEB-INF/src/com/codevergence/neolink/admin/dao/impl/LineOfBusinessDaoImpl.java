package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.LineOfBusinessDao;
import com.codevergence.neolink.admin.model.LineOfBusiness;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("lineOfBusinessDao")
public class LineOfBusinessDaoImpl implements LineOfBusinessDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<LineOfBusiness> getAll() throws DataAccessException {
		List<LineOfBusiness> results = new ArrayList<LineOfBusiness>();
		results  = jdbcTemplate.query(
				"SELECT * FROM Neolink_Master_Unit_Business order by show_order",
				new LineOfBusinessMapper());
		return results;
	}

	public LineOfBusiness getById(long id) throws DataAccessException {
		List<LineOfBusiness> results = new ArrayList<LineOfBusiness>();
		results  = jdbcTemplate.query(
				"SELECT * FROM Neolink_Master_Unit_Business WHERE row_id = ?",
				new Object[] { id }, 
				new LineOfBusinessMapper());
		LineOfBusiness result = DataAccessUtils.singleResult(results);
		return result;
	}

	public void insert(LineOfBusiness lob) throws DataAccessException {
		jdbcTemplate.update(
				"INSERT INTO Neolink_Master_Unit_Business (ROW_ID, unit_business_id, unit_business_name, color_code, email_group, show_order) " +
				"VALUES ((select max(row_id) FROM Neolink_Master_Branch_Class)+1, ?, ?, ?, ?, ?)",
				new Object[] { lob.getUnitBusinessId(), lob.getUnitBusiness(), lob.getColorCode(), lob.getEmailGroup(), lob.getShowOrder() });
	}

	public void update(LineOfBusiness lob) throws DataAccessException {
		jdbcTemplate.update(
				"UPDATE Neolink_Master_Unit_Business SET unit_business_id = ?, unit_business_name = ?, " +
				"color_code = ?, email_group = ?, show_order = ? WHERE row_id = ?",
				new Object[] { lob.getUnitBusinessId(), lob.getUnitBusiness(), lob.getColorCode(), lob.getEmailGroup(), lob.getShowOrder(), lob.getId() });
	}

	public void delete(long id) throws DataAccessException {
		this.jdbcTemplate.update("DELETE FROM Neolink_Master_Unit_Business where row_id = ?", id);
	}
	
	private static final class LineOfBusinessMapper implements RowMapper<LineOfBusiness> {
		public LineOfBusiness mapRow(ResultSet rs, int arg1) throws SQLException {
			LineOfBusiness lob = new LineOfBusiness();
			lob.setId(rs.getInt("row_id"));
			lob.setUnitBusinessId(rs.getString("unit_business_id"));
			lob.setUnitBusiness(rs.getString("unit_business_name"));
			lob.setColorCode(rs.getString("color_code"));
			lob.setEmailGroup(rs.getString("email_group"));
			lob.setShowOrder(rs.getString("show_order"));
			return lob;
		}
	}

	public LineOfBusiness getByUnitBusinessId(String unitBusinessId)
			throws DataAccessException {
		List<LineOfBusiness> results = new ArrayList<LineOfBusiness>();
		results  = jdbcTemplate.query(
				"SELECT * FROM Neolink_Master_Unit_Business WHERE UNIT_BUSINESS_ID = ?",
				new Object[] { unitBusinessId }, 
				new LineOfBusinessMapper());
		LineOfBusiness result = DataAccessUtils.singleResult(results);
		return result;
	}
}
