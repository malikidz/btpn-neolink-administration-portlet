package com.codevergence.neolink.admin.model;

public class RegionMapping extends Region {
	private Province provinse;
	private LineOfBusiness lob;
	public Province getProvinse() {
		return provinse;
	}
	public void setProvinse(Province provinse) {
		this.provinse = provinse;
	}
	public LineOfBusiness getLob() {
		return lob;
	}
	public void setLob(LineOfBusiness lob) {
		this.lob = lob;
	}
}
