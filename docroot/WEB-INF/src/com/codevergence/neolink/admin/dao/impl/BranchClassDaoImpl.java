package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.BranchClassDao;
import com.codevergence.neolink.admin.model.BranchClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("branchClassDao")
public class BranchClassDaoImpl implements BranchClassDao {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SQL_GET_ALL =
			"SELECT * FROM Neolink_Master_Branch_Class ORDER BY branch_class_id";
	public List<BranchClass> getAll() {
		List<BranchClass> results = jdbcTemplate.query(
				SQL_GET_ALL,
				new BranchClassMapper());
		return results;
	}

	private static final String SQL_GET_BY_ID =
			"SELECT * FROM Neolink_Master_Branch_Class WHERE ROW_ID = ?";
	public BranchClass getBranchClassById(int rowId) {
		List<BranchClass> results = new ArrayList<BranchClass>();
		results  = jdbcTemplate.query(
				SQL_GET_BY_ID,
				new Object[] { rowId }, 
				new BranchClassMapper());
		BranchClass result = DataAccessUtils.singleResult(results);
		return result;
	}

	private static final class BranchClassMapper implements RowMapper<BranchClass> {
		public BranchClass mapRow(ResultSet rs, int arg1) throws SQLException {
			BranchClass branchClass = new BranchClass();
			branchClass.setRowId(rs.getInt("ROW_ID"));
			branchClass.setBranchClassId(rs.getString("BRANCH_CLASS_ID"));
			branchClass.setBranchClassMapMarker(rs.getString("BRANCH_CLASS_MAP_MARKER"));
			branchClass.setBranchClassName(rs.getString("BRANCH_CLASS_NAME"));
			return branchClass;
		}
	}

	private static final String SQL_INSERT =
			"INSERT INTO Neolink_Master_Branch_Class (ROW_ID, BRANCH_CLASS_ID, BRANCH_CLASS_NAME, BRANCH_CLASS_MAP_MARKER) " +
			"VALUES ((select max(row_id) FROM Neolink_Master_Branch_Class)+1, ?, ?, ?);";
	public void insert(BranchClass branchClass) {
		jdbcTemplate.update(
				SQL_INSERT,
				new Object[] {branchClass.getBranchClassId(), branchClass.getBranchClassName(),
						branchClass.getBranchClassMapMarker()});
	}

	private static final String SQL_UPDATE =
			"UPDATE Neolink_Master_Branch_Class SET BRANCH_CLASS_ID = ?, BRANCH_CLASS_NAME = ?, BRANCH_CLASS_MAP_MARKER = ? " +
			"WHERE ROW_ID = ?";
	public void update(BranchClass branchClass) {
		jdbcTemplate.update(
				SQL_UPDATE,
				new Object[] {branchClass.getBranchClassId(), branchClass.getBranchClassName(),
						branchClass.getBranchClassMapMarker(), branchClass.getRowId()});
	}

	public void delete(int rowId) {
		jdbcTemplate.update("DELETE FROM Neolink_Master_Branch_Class WHERE row_id = ?", rowId);
	}

}
