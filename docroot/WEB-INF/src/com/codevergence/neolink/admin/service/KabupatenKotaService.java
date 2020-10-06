package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.KabupatenKota;

import java.util.List;

public interface KabupatenKotaService {
	public List<KabupatenKota> getAll();
	public KabupatenKota getById(int id);
	public void insert(KabupatenKota kabupatenKota);
	public void update(KabupatenKota kabupatenKota);
	public void delete(int id);
	public void active(int id, boolean isActive); 
	public void updateNeolinkKabupaten(String oldKabupatenKota, String newKabupatenKota);
	public KabupatenKota withIdAndName(String kabupatenKode, String name);
}
