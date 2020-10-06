package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.KabupatenKotaDao;
import com.codevergence.neolink.admin.model.KabupatenKota;
import com.codevergence.neolink.admin.model.Province;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("kabupatenKotaDao")
public class KabupatenKotaDaoImpl implements KabupatenKotaDao{

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getOldNameKabupatenKota(KabupatenKota oldKabupatenKota){
		try {
			String value = jdbcTemplate.queryForObject("SELECT kabupaten_name FROM Neolink_Master_Kabupaten WHERE KABUPATEN_ID = ?",
					new Object[] { oldKabupatenKota.getKabupatenId() },
					String.class);
			return value;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	public List<KabupatenKota> getAll() throws DataAccessException{
		List<KabupatenKota> results = new ArrayList<KabupatenKota>();
		results =  this.jdbcTemplate.query("SELECT a.*, b.PROPINSI_ID, b.PROPINSI_NAME FROM Neolink_Master_Kabupaten a " +
		"JOIN Neolink_Master_Propinsi b ON a.PROPINSI_ID = b.PROPINSI_ID " + 
		"WHERE active = 1 order by b.PROPINSI_NAME, a.KABUPATEN_NAME", 
				new KabupatenKotaMapper());
		return results;
	}

	private static final class KabupatenKotaMapper implements RowMapper<KabupatenKota> {
		public KabupatenKota mapRow(ResultSet rs, int arg1) throws SQLException {
			KabupatenKota kabupatenKota = new KabupatenKota();
			kabupatenKota.setId(rs.getInt("ROW_ID"));
			kabupatenKota.setKabupatenId(rs.getString("KABUPATEN_ID"));
			kabupatenKota.setName(rs.getString("KABUPATEN_NAME"));
			kabupatenKota.setActive(rs.getBoolean("active"));
			kabupatenKota.setPropinsiId(rs.getString("PROPINSI_ID"));
			
			Province provinsi = new Province();
			provinsi.setPropinsiId(rs.getString("PROPINSI_ID"));
			provinsi.setPropinsiName(rs.getString("PROPINSI_NAME"));
			
			kabupatenKota.setProvinsi(provinsi);
			return kabupatenKota;
		}
	}

	public KabupatenKota getById(int id) throws DataAccessException {
		List<KabupatenKota> results = new ArrayList<KabupatenKota>();
		results  = jdbcTemplate.query("SELECT a.*, b.PROPINSI_ID, b.PROPINSI_NAME FROM Neolink_Master_Kabupaten a " +
				"JOIN Neolink_Master_Propinsi b ON a.PROPINSI_ID = b.PROPINSI_ID " + 
				"WHERE active = 1 AND a.ROW_ID = ?",
				new Object[] { id }, 
				new KabupatenKotaMapper());
		KabupatenKota result = DataAccessUtils.singleResult(results);
		return result;
	}

	public void insert(KabupatenKota kabupatenKota) throws DataAccessException {
		jdbcTemplate.update(
				"INSERT INTO Neolink_Master_Kabupaten (ROW_ID, KABUPATEN_ID, KABUPATEN_NAME, PROPINSI_ID, PROPINSI_NAME, active) " +
				"VALUES ((select max(row_id) from Neolink_Master_Kabupaten)+1, ?, ?, ?, ?, ?)",
				new Object[] { kabupatenKota.getKabupatenId(), kabupatenKota.getName(), 
						kabupatenKota.getProvinsi().getPropinsiId(), kabupatenKota.getProvinsi().getPropinsiName(), kabupatenKota.isActive() });
	}

	public void update(KabupatenKota kabupatenKota) throws DataAccessException {
		if(kabupatenKota != null){
			if (kabupatenKota.getKabupatenId() != null && kabupatenKota.getKabupatenId().length() > 0){
				String oldKabupatenKota = getOldNameKabupatenKota(kabupatenKota);
				if(oldKabupatenKota != null && oldKabupatenKota.length() > 0){
					updateNeolinkKabupaten(oldKabupatenKota, kabupatenKota.getName());
				}
				
			}
			jdbcTemplate.update("UPDATE Neolink_Master_Kabupaten SET KABUPATEN_ID = ?, KABUPATEN_NAME = ?, PROPINSI_ID = ?, PROPINSI_NAME = ?, active = ? WHERE ROW_ID = ?",
					new Object[] { kabupatenKota.getKabupatenId(), kabupatenKota.getName(), 
							kabupatenKota.getProvinsi().getPropinsiId(), kabupatenKota.getProvinsi().getPropinsiName(), kabupatenKota.isActive(), kabupatenKota.getId() });
		}
		
		
		
	}

	public void delete(int id) throws DataAccessException {
		jdbcTemplate.update(
				"DELETE Neolink_Master_Kabupaten WHERE ROW_ID = ? ",
				new Object[] { id });
	}

	public void active(int id, boolean isActive) throws DataAccessException {
		jdbcTemplate.update(
				"UPDATE Neolink_Master_Kabupaten SET active = ? WHERE ROW_ID = ?",
				new Object[] { isActive, id });
	}

	public void updateNeolinkKabupaten(String oldKabupatenKota,
			String newKabupatenKota) {
		jdbcTemplate.update(
				"UPDATE Neolink_Geokabupaten SET nama_kabupaten = ? WHERE nama_kabupaten = ?",
				new Object[] { newKabupatenKota, oldKabupatenKota });
		jdbcTemplate.update(
				"UPDATE Neolink_Master_Branch SET KABUPATEN_NAME = ? WHERE KABUPATEN_NAME = ?",
				new Object[] { newKabupatenKota, oldKabupatenKota });
	}

	public KabupatenKota withIdAndName(String kabupatenKode, String name)  {
		List<KabupatenKota> results = new ArrayList<KabupatenKota>();
		KabupatenKota result = null;
		try {
			results  = jdbcTemplate.query("SELECT a.*, b.PROPINSI_ID, b.PROPINSI_NAME FROM Neolink_Master_Kabupaten a " +
					"JOIN Neolink_Master_Propinsi b ON a.PROPINSI_ID = b.PROPINSI_ID " + 
					"WHERE a.KABUPATEN_ID = ? AND KABUPATEN_NAME = ?",
					new Object[] { kabupatenKode, name }, 
					new KabupatenKotaMapper());
			

					result = DataAccessUtils.singleResult(results);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
