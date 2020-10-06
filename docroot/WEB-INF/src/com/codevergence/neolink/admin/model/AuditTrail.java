package com.codevergence.neolink.admin.model;

import java.util.Date;

public class AuditTrail {
	private String createdUserId;
	private String updatedUserId;
	private String createdUserIpAddr;
	private String updatedUserIpAddr;
	private Date createdDate;
	private Date updatedDate;
	public String getCreatedUserId() {
		return createdUserId;
	}
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}
	public String getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	public String getCreatedUserIpAddr() {
		return createdUserIpAddr;
	}
	public void setCreatedUserIpAddr(String createdUserIpAddr) {
		this.createdUserIpAddr = createdUserIpAddr;
	}
	public String getUpdatedUserIpAddr() {
		return updatedUserIpAddr;
	}
	public void setUpdatedUserIpAddr(String updatedUserIpAddr) {
		this.updatedUserIpAddr = updatedUserIpAddr;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
