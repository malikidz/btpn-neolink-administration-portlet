package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.ProvinceDao;
import com.codevergence.neolink.admin.model.Province;
import com.codevergence.neolink.admin.model.ZonaBI;

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

@Repository("provinceDao")
public class ProvinceDaoImpl implements ProvinceDao {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(Province province) throws DataAccessException {
		jdbcTemplate.update(
				"INSERT INTO Neolink_Master_Propinsi (ROW_ID, PROPINSI_ID, PROPINSI_NAME, IMAGE_ID, ZONA_BI_ID, COLOR) " +
				"VALUES ((select max(row_id) from Neolink_Master_Propinsi)+1, ?, ?, ?, ?, ?)",
				new Object[] { province.getPropinsiId(), province.getPropinsiName(), province.getImageId(), province.getZonaBiId(),
						province.getColorCode() });
		
	}

	public void update(Province province) throws DataAccessException {
		jdbcTemplate.update(
				"UPDATE Neolink_Master_Propinsi SET PROPINSI_ID= ? , PROPINSI_NAME= ? , IMAGE_ID= ? , ZONA_BI_ID = ?, COLOR = ? " +
				"WHERE ROW_ID = ?",
				new Object[] {province.getPropinsiId(), province.getPropinsiName(), province.getImageId(), province.getZonaBiId(), 
						province.getColorCode(), province.getRowId()  });
		
	}

	public void delete(long rowId) throws DataAccessException {
		this.jdbcTemplate.update("DELETE FROM Neolink_Master_Propinsi where row_id = ?", rowId);
		
	}

	public Province withId(long id) throws DataAccessException {
		List<Province> results = new ArrayList<Province>();
		results  = jdbcTemplate.query(
				"SELECT provinsi.*, zona.nama_zona " + 
				"FROM Neolink_Master_Propinsi as provinsi " +
				"	LEFT JOIN Neolink_Zona_BI as zona ON provinsi.ZONA_BI_ID = zona.id " + 
				"WHERE ROW_ID = ?",
				new Object[] { id },
				new ProvinceMapper());
		Province result = DataAccessUtils.singleResult(results);
		return result;
	}

	public List<Province> getAll() throws DataAccessException {
		List<Province> results = new ArrayList<Province>();
		results  = jdbcTemplate.query(
				"SELECT provinsi.*, zona.nama_zona " + 
				"FROM Neolink_Master_Propinsi as provinsi " +
				"	LEFT JOIN Neolink_Zona_BI as zona ON provinsi.ZONA_BI_ID = zona.id " +
				"ORDER BY zona.nama_zona",
				new ProvinceMapper());
		return results;
	}

	private static final class ProvinceMapper implements RowMapper<Province> {
		public Province mapRow(ResultSet rs, int arg1) throws SQLException {
			Province province = new Province();
			province.setRowId(rs.getLong("ROW_ID"));
			province.setPropinsiId(rs.getString("PROPINSI_ID"));
			province.setPropinsiName(rs.getString("PROPINSI_NAME"));
			province.setImageId(rs.getString("IMAGE_ID"));
			province.setZonaBiId(rs.getLong("ZONA_BI_ID"));
			province.setColorCode(rs.getString("COLOR"));
//			province.setRegion(rs.getString("REGION"));
//			province.setRegionId(rs.getLong("REGION_ID"));
			//map zonaBi
			ZonaBI zonaBi = new ZonaBI();
			//zonaBi.setId(rs.getLong("IMAGE_ID"));
			zonaBi.setNamaZona(rs.getString("nama_zona"));
			province.setZonaBi(zonaBi);
			return province;
		}
	}

	public Hashtable<Long, Province> getAllAsHash() throws DataAccessException {
		List<Province> results = new ArrayList<Province>();
		Hashtable<Long, Province> resultHash = new Hashtable<Long, Province>();
		
		results  = this.getAll();
		for (int i = 0; i < results.size(); i++) {
			Province province  = results.get(i);
			resultHash.put(new Long(province.getRowId()), province);
		}
		return resultHash;
	}

}
