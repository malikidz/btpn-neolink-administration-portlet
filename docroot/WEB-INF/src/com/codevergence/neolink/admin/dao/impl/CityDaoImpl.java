package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.CityDao;
import com.codevergence.neolink.admin.model.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("cityDao")
public class CityDaoImpl implements CityDao {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private static String SQL_ALL = "select c.city_id, c.city_name, c.id , p.ROW_ID, p.PROPINSI_NAME from Neolink_Master_Propinsi p join Neolink_Master_City c on c.propinsi_id = p.ROW_ID";
	private static String SQL_WITH_ID = SQL_ALL + " WHERE ID = ?";
	private static String SQL_DELETE = "DELETE FROM Neolink_Master_City WHERE ID = ?";
	private static String SQL_INSERT = "INSERT INTO Neolink_Master_City (PROPINSI_ID, CITY_NAME, CITY_ID) VALUES (?,?,?)";
	private static String SQL_UPDATE = "UPDATE Neolink_Master_City set PROPINSI_ID = ?, CITY_NAME = ?, CITY_ID = ? WHERE ID = ?";
	
	private static final class CityMapper implements RowMapper<City> {
		public City mapRow(ResultSet rs, int arg1) throws SQLException {
			City city = new City();
			city.setCityId(rs.getString("city_id"));
			city.setCityName(rs.getString("city_name"));
			city.setId(rs.getInt("id"));
			city.setPropinsiId(rs.getInt("row_id"));
			city.setPropinsiName(rs.getString("propinsi_name"));
			return  city;
		}
	}
	
	public List<City> getAll() {
		List<City> results = new ArrayList<City>();
		results = jdbcTemplate.query(SQL_ALL, new CityMapper());
		return results;
	}

	public List<City> getAllForPropinsi(String propinsiName) {
		List<City> results = new ArrayList<City>();
		results = jdbcTemplate.query(SQL_ALL, new CityMapper());
		return results;
	}

	public City withId(int cityId) {
		City city = jdbcTemplate.queryForObject(SQL_WITH_ID, new Object[]{cityId},new CityMapper());
		return city;
	}

	public void delete(int cityId) {
		this.jdbcTemplate.update(SQL_DELETE, cityId);
		
	}

	public void insert(City city) {
		jdbcTemplate.update(
				SQL_INSERT,
				new Object[] { city.getPropinsiId(), city.getCityName(), city.getCityId() });
		
	}

	public void update(City city) {
		jdbcTemplate.update(
				SQL_UPDATE,
				new Object[] { city.getPropinsiId(), city.getCityName(), city.getCityId(), city.getId() });
		
	}



}
