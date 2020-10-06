package com.codevergence.neolink.admin.model;

public class ZonaBI {
	private long id;
	private String namaZona;
	private String colorCode;
	private double koefisien;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNamaZona() {
		return namaZona;
	}
	public void setNamaZona(String namaZona) {
		this.namaZona = namaZona;
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
	public double getKoefisien() {
		return koefisien;
	}
	public void setKoefisien(double koefisien) {
		this.koefisien = koefisien;
	}
}
