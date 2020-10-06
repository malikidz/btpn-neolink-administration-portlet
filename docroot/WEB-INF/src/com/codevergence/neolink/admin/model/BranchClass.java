package com.codevergence.neolink.admin.model;

public class BranchClass extends AuditTrail {
	private int rowId;
	private String branchClassId;
	private String branchClassName;
	private String branchClassMapMarker;

	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public String getBranchClassId() {
		return branchClassId;
	}
	public void setBranchClassId(String branchClassId) {
		this.branchClassId = branchClassId;
	}
	public String getBranchClassName() {
		return branchClassName;
	}
	public void setBranchClassName(String branchClassName) {
		this.branchClassName = branchClassName;
	}
	public String getBranchClassMapMarker() {
		return branchClassMapMarker;
	}
	public void setBranchClassMapMarker(String branchClassMapMarker) {
		this.branchClassMapMarker = branchClassMapMarker;
	}
}
