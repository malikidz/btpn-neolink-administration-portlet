package com.codevergence.neolink.admin.model;

public class BranchType {

	long rowId;
	int showOrder;
	String branchTypeId, branchTypeName, branchTypeMapMarker, sandiKantor, colorCode;
	private boolean perhitunganAmi, mapView;
	
	public long getRowId() {
		return rowId;
	}
	public void setRowId(long rowId) {
		this.rowId = rowId;
	}
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	public String getBranchTypeId() {
		return branchTypeId;
	}
	public void setBranchTypeId(String branchTypeId) {
		this.branchTypeId = branchTypeId;
	}
	public String getBranchTypeName() {
		return branchTypeName;
	}
	public void setBranchTypeName(String branchTypeName) {
		this.branchTypeName = branchTypeName;
	}
	public String getBranchTypeMapMarker() {
		return branchTypeMapMarker;
	}
	public void setBranchTypeMapMarker(String branchTypeMapMarker) {
		this.branchTypeMapMarker = branchTypeMapMarker;
	}
	public String getSandiKantor() {
		return sandiKantor;
	}
	public void setSandiKantor(String sandiKantor) {
		this.sandiKantor = sandiKantor;
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
	public boolean isPerhitunganAmi() {
		return perhitunganAmi;
	}
	public void setPerhitunganAmi(boolean perhitunganAmi) {
		this.perhitunganAmi = perhitunganAmi;
	}
	public boolean isMapView() {
		return mapView;
	}
	public void setMapView(boolean mapView) {
		this.mapView = mapView;
	}
	
}
