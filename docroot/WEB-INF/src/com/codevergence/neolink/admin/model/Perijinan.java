package com.codevergence.neolink.admin.model;

import java.util.Date;

public class Perijinan {
	private long id;
	private Branch branch;
	private String namaDokumen;
	private String noDokumen;
	private Date tglKeluarDokumen;
	private Date tglBerakhirDokumen;
	private long fileId;
	private boolean active;
	private long folderId;
	private long groupId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public String getNamaDokumen() {
		return namaDokumen;
	}
	public void setNamaDokumen(String namaDokumen) {
		this.namaDokumen = namaDokumen;
	}
	public String getNoDokumen() {
		return noDokumen;
	}
	public void setNoDokumen(String noDokumen) {
		this.noDokumen = noDokumen;
	}
	public Date getTglKeluarDokumen() {
		return tglKeluarDokumen;
	}
	public void setTglKeluarDokumen(Date tglKeluarDokumen) {
		this.tglKeluarDokumen = tglKeluarDokumen;
	}
	public Date getTglBerakhirDokumen() {
		return tglBerakhirDokumen;
	}
	public void setTglBerakhirDokumen(Date tglBerakhirDokumen) {
		this.tglBerakhirDokumen = tglBerakhirDokumen;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
}
