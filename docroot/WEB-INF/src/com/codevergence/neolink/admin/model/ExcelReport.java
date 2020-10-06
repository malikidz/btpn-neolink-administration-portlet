package com.codevergence.neolink.admin.model;

import java.util.Date;

public class ExcelReport {

	private int rowId;
	private String branchId, branchName, kabupatenKota, propinsi, description, branchClassId, parentId;
	private Date errorDate;
	
	
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getKabupatenKota() {
		return kabupatenKota;
	}
	public void setKabupatenKota(String kabupatenKota) {
		this.kabupatenKota = kabupatenKota;
	}
	public String getPropinsi() {
		return propinsi;
	}
	public void setPropinsi(String propinsi) {
		this.propinsi = propinsi;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getErrorDate() {
		return errorDate;
	}
	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}
	public String getBranchClassId() {
		return branchClassId;
	}
	public void setBranchClassId(String branchClassId) {
		this.branchClassId = branchClassId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
