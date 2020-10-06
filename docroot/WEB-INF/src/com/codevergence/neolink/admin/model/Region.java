package com.codevergence.neolink.admin.model;

public class Region {

	private long id;
	private String name;
	private String regionCode;
	private long unitBusinessId;
	private String unitBusinessName;
	private long provinceId;
	private String provinceName;
	private long mappingId;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public long getUnitBusinessId() {
		return unitBusinessId;
	}
	public void setUnitBusinessId(long unitBusinessId) {
		this.unitBusinessId = unitBusinessId;
	}
	public long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}
	public String getUnitBusinessName() {
		return unitBusinessName;
	}
	public void setUnitBusinessName(String unitBusinessName) {
		this.unitBusinessName = unitBusinessName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public long getMappingId() {
		return mappingId;
	}
	public void setMappingId(long mappingId) {
		this.mappingId = mappingId;
	}
	
	public String getPropinsiInfo(){
		return this.provinceName + "|" + this.provinceId;
	}
	
	public String getRegionInfo(){
		return this.name + "|" + this.id;
	}
	
}
