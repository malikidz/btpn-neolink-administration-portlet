package com.codevergence.neolink.admin.model;


public class PerijinanBI extends Perijinan{
	private boolean efective;
	
	//recap & recap efective
	private boolean recap;
	private boolean recapEfective;

	public boolean isEfective() {
		return efective;
	}

	public void setEfective(boolean efective) {
		this.efective = efective;
	}

	public boolean isRecap() {
		return recap;
	}

	public void setRecap(boolean recap) {
		this.recap = recap;
	}

	public boolean isRecapEfective() {
		return recapEfective;
	}

	public void setRecapEfective(boolean recapEfective) {
		this.recapEfective = recapEfective;
	}
}
