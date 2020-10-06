package com.codevergence.neolink.admin.model;

import java.util.Date;

public class PerijinanApproval {
	private long id;
	private long fileId;
	private long folderId;
	private long groupId;
	private String fileName;
	private String title;
	private long userId;
	private long approvalUserIdl;
	private String notes;
	private boolean approved;
	private Date updatedDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	public long getFolderId() {
		return folderId;
	}
	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getApprovalUserIdl() {
		return approvalUserIdl;
	}
	public void setApprovalUserIdl(long approvalUserIdl) {
		this.approvalUserIdl = approvalUserIdl;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
