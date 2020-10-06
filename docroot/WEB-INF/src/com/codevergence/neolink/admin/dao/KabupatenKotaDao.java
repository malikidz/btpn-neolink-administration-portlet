package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.KabupatenKota;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface KabupatenKotaDao {
	public List<KabupatenKota> getAll() throws DataAccessException;
	public KabupatenKota getById(int id) throws DataAccessException;
	public void insert(KabupatenKota kabupatenKota) throws DataAccessException;
	public void update(KabupatenKota kabupatenKota) throws DataAccessException;
	public void delete(int id) throws DataAccessException;
	public void active(int id, boolean isActive) throws DataAccessException; 
	public void updateNeolinkKabupaten(String oldKabupatenKota, String newKabupatenKota);
	public KabupatenKota withIdAndName(String kabupatenKode, String name);
}
