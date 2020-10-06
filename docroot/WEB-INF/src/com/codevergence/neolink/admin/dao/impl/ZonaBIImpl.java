package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.ZonaBIDao;
import com.codevergence.neolink.admin.model.ZonaBI;

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

@Repository("zonaBiDao")
public class ZonaBIImpl implements ZonaBIDao {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ZonaBI> getAll() throws DataAccessException {
		List<ZonaBI> zonabis = new ArrayList<ZonaBI>();
		zonabis  = jdbcTemplate.query(
				"SELECT * FROM Neolink_Zona_BI ORDER BY nama_zona",
				new ZonaBIMapper());
		return zonabis;
	}

	public void insert(ZonaBI zonabi) throws DataAccessException {
		jdbcTemplate.update(
				"INSERT INTO Neolink_Zona_BI (nama_zona, color_code, koefisien) VALUES (?, ?, ?)",
				new Object[] { zonabi.getNamaZona(), zonabi.getColorCode(), zonabi.getKoefisien() });
	}

	public void update(ZonaBI zonabi) throws DataAccessException {
		jdbcTemplate.update(
				"UPDATE Neolink_Zona_BI SET nama_zona = ?, color_code = ?, koefisien = ? WHERE id = ?",
				new Object[] { zonabi.getNamaZona(), zonabi.getColorCode(), zonabi.getKoefisien(), zonabi.getId() });
	}

	private static final class ZonaBIMapper implements RowMapper<ZonaBI> {
		public ZonaBI mapRow(ResultSet rs, int arg1) throws SQLException {
			ZonaBI zonabi = new ZonaBI();
			zonabi.setId(rs.getLong("id"));
			zonabi.setNamaZona(rs.getString("nama_zona"));
			zonabi.setColorCode(rs.getString("color_code"));
			zonabi.setKoefisien(rs.getDouble("koefisien"));
			return zonabi;
		}
	}

	public ZonaBI getById(long id) throws DataAccessException {
		List<ZonaBI> results = new ArrayList<ZonaBI>();
		results  = jdbcTemplate.query(
				"SELECT * FROM Neolink_Zona_BI WHERE id = ?",
				new Object[] { id }, 
				new ZonaBIMapper());
		ZonaBI result = DataAccessUtils.singleResult(results);
		return result;
	}

	public void delete(long id) throws DataAccessException {
		this.jdbcTemplate.update("DELETE FROM Neolink_Zona_BI where id = ?", id);
	}
}
