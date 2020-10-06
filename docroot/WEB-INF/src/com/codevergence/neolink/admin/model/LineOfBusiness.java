package com.codevergence.neolink.admin.model;

public class LineOfBusiness {
	private int id;
	private String unitBusinessId;
	private String unitBusiness;
	private String colorCode;
	private String emailGroup;
	private String showOrder;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnitBusinessId() {
		return unitBusinessId;
	}
	public void setUnitBusinessId(String unitBusinessId) {
		this.unitBusinessId = unitBusinessId;
	}
	public String getUnitBusiness() {
		return unitBusiness;
	}
	public void setUnitBusiness(String unitBusiness) {
		this.unitBusiness = unitBusiness;
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
	public String getEmailGroup() {
		return emailGroup;
	}
	public void setEmailGroup(String emailGroup) {
		this.emailGroup = emailGroup;
	}
	public String getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}
	
}
