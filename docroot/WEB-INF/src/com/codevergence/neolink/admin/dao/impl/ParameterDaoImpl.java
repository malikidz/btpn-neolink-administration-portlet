package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.ParameterDao;
import com.codevergence.neolink.admin.model.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("parameterDao")
public class ParameterDaoImpl implements ParameterDao {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getValue(String param) throws DataAccessException {
		try {
			String value = jdbcTemplate.queryForObject("SELECT value FROM Neolink_Parameter WHERE param = ?",
					new Object[] { param },
					String.class);
			return value;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	public List<Parameter> getParameters() throws DataAccessException {
		List<Parameter> karyawans = new ArrayList<Parameter>();
		karyawans  = jdbcTemplate.query("SELECT * FROM Neolink_Parameter ORDER BY param asc",
				new ParameterMapper());
		return karyawans;
	}

	private static final class ParameterMapper implements RowMapper<Parameter> {
		public Parameter mapRow(ResultSet rs, int arg1) throws SQLException {
			Parameter karyawan = new Parameter();
			karyawan.setParam(rs.getString("param"));
			karyawan.setValue(rs.getString("value"));
			return karyawan;
		}
	}

	public void save(Parameter parameter) throws DataAccessException {
		int row = jdbcTemplate.update("UPDATE Neolink_Parameter SET value = ? WHERE param = ?",
				new Object[] { parameter.getValue(), parameter.getParam() });
		if (row == 0) {
			row = jdbcTemplate.update("INSERT INTO Neolink_Parameter (param, value) VALUES (?, ?)",
					new Object[] { parameter.getParam(), parameter.getValue() });
		}
	}
	
	public void execute(String sql) throws DataAccessException{
		jdbcTemplate.update(sql);
	}
}
