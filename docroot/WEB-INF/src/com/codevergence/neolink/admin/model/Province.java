package com.codevergence.neolink.admin.model;

public class Province {
	private long rowId, zonaBiId, regionId;
	private ZonaBI zonaBi;
	private String propinsiId, propinsiName, imageId, colorCode, region;
	
	public long getRowId() {
		return rowId;
	}
	public void setRowId(long rowId) {
		this.rowId = rowId;
	}
	public long getZonaBiId() {
		return zonaBiId;
	}
	public void setZonaBiId(long zonaBiId) {
		this.zonaBiId = zonaBiId;
	}
	public ZonaBI getZonaBi() {
		return zonaBi;
	}
	public void setZonaBi(ZonaBI zonaBi) {
		this.zonaBi = zonaBi;
	}
	public String getPropinsiId() {
		return propinsiId;
	}
	public void setPropinsiId(String propinsiId) {
		this.propinsiId = propinsiId;
	}
	public String getPropinsiName() {
		return propinsiName;
	}
	public void setPropinsiName(String propinsiName) {
		this.propinsiName = propinsiName;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getColorCode() {
		if(colorCode != null){
			if(!colorCode.contains("#")){
				colorCode = "#" + colorCode;
			}
		}
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public long getRegionId() {
		return regionId;
	}
	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}
}
