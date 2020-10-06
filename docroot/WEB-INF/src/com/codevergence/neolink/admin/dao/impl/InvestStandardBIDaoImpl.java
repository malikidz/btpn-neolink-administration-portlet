package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.InvestStandardBIDao;
import com.codevergence.neolink.admin.model.InvestStandardBI;

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

@Repository("investStandardBIDao")
public class InvestStandardBIDaoImpl implements InvestStandardBIDao{

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(InvestStandardBI investStandardBI)
			throws DataAccessException {
		jdbcTemplate.update(
				"INSERT INTO INVES_STANDARD_BI (branch_type_id, name, invest_value, book_type) VALUES ( ?, ?, ?, ?)",
				new Object[] { investStandardBI.getBranchTypeId(),  investStandardBI.getName(), investStandardBI.getInvestValue(), investStandardBI.getBookType()});
		
		
	}

	public void update(InvestStandardBI investStandardBI)
			throws DataAccessException {
		jdbcTemplate.update(
				"UPDATE INVES_STANDARD_BI SET branch_type_id = ?,   name = ?,   invest_value = ?,   book_type = ? WHERE ID = ?",
				new Object[] { investStandardBI.getBranchTypeId(), investStandardBI.getName(), investStandardBI.getInvestValue(), investStandardBI.getBookType(), investStandardBI.getId()  });
		
	}

	public void delete(long rowId) throws DataAccessException {
		this.jdbcTemplate.update("DELETE FROM INVES_STANDARD_BI where id = ?", rowId);
		
	}

	public InvestStandardBI withId(long id) throws DataAccessException {
		List<InvestStandardBI> results = new ArrayList<InvestStandardBI>();
		results  = jdbcTemplate.query("SELECT * FROM INVES_STANDARD_BI WHERE id = ?",
				new Object[] { id }, new InvestStandardBIMapper());
		InvestStandardBI result = DataAccessUtils.singleResult(results);
		return result;
	}

	public List<InvestStandardBI> getAll() throws DataAccessException {
		List<InvestStandardBI> results = new ArrayList<InvestStandardBI>();
		results  = jdbcTemplate.query("SELECT * FROM INVES_STANDARD_BI order by id",
				new InvestStandardBIMapper());
		return results;
	}
	
	private static final class InvestStandardBIMapper implements RowMapper<InvestStandardBI> {
		public InvestStandardBI mapRow(ResultSet rs, int arg1) throws SQLException {
			InvestStandardBI investStandardBI = new InvestStandardBI();
			investStandardBI.setBranchTypeId(rs.getLong("branch_type_id"));
			investStandardBI.setName(rs.getString("name"));
			investStandardBI.setInvestValue(rs.getDouble("invest_value"));
			investStandardBI.setBookType(rs.getString("book_type"));
			investStandardBI.setId(rs.getLong("id"));
			return investStandardBI;
		}
	}

}
