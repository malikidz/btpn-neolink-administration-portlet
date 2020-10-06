package com.codevergence.neolink.admin.model;

import java.util.Date;

public class Branch {
	private int id;
	private String branchId;
	private String name;
	private String branchEmailAddress;
	private String headEmailAddress;
	private Date startSewaDate;
	private Date endSewaDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranchEmailAddress() {
		return branchEmailAddress;
	}
	public void setBranchEmailAddress(String branchEmailAddress) {
		this.branchEmailAddress = branchEmailAddress;
	}
	public String getHeadEmailAddress() {
		return headEmailAddress;
	}
	public void setHeadEmailAddress(String headEmailAddress) {
		this.headEmailAddress = headEmailAddress;
	}
	public Date getStartSewaDate() {
		return startSewaDate;
	}
	public void setStartSewaDate(Date startSewaDate) {
		this.startSewaDate = startSewaDate;
	}
	public Date getEndSewaDate() {
		return endSewaDate;
	}
	public void setEndSewaDate(Date endSewaDate) {
		this.endSewaDate = endSewaDate;
	}
}
