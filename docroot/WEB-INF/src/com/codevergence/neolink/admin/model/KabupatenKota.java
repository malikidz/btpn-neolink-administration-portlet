package com.codevergence.neolink.admin.model;

public class KabupatenKota {
	private int id;
	private String kabupatenId;
	private String propinsiId;
	private String name;
	private boolean active;
	
	private Province provinsi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKabupatenId() {
		return kabupatenId;
	}
	public void setKabupatenId(String kabupatenId) {
		this.kabupatenId = kabupatenId;
	}
	public String getName() {
		if(name != null && name.length() > 0 ){
			name = name.trim();
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Province getProvinsi() {
		return provinsi;
	}
	public void setProvinsi(Province provinsi) {
		this.provinsi = provinsi;
	}
	public String getPropinsiId() {
		return propinsiId;
	}
	public void setPropinsiId(String propinsiId) {
		this.propinsiId = propinsiId;
	}
}
