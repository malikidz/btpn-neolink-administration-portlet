package com.codevergence.neolink.admin.model;

public class InvestStandardBI {
	long branchTypeId, id;
	String name, bookType;
	double investValue, fullInvestValue;
	BranchType branchType;
	
	
	public long getBranchTypeId() {
		return branchTypeId;
	}
	public void setBranchTypeId(long branchTypeId) {
		this.branchTypeId = branchTypeId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public double getInvestValue() {
		return investValue;
	}
	public void setInvestValue(double investValue) {
		this.investValue = investValue;
	}
	public BranchType getBranchType() {
		return branchType;
	}
	public void setBranchType(BranchType branchType) {
		this.branchType = branchType;
	}
	public double getFullInvestValue() {
		fullInvestValue = this.getInvestValue() * 1000000000;
		return fullInvestValue;
	}
	public void setFullInvestValue(double fullInvestValue) {
		this.fullInvestValue = fullInvestValue;
	}
	
	
}
