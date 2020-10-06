package com.codevergence.neolink.admin.model;

public class EmailScheduler {

	//global
	private long id;
	private String name;//admin,pic
	private long emailTemplateId;
	
	//for admin only, 
	//email PIC di ambil dari tabel Neolink_Master_Branch (branch_email) 
	//usulkan di meeting dengan menggunakan email address: admin_$BRANCH_CODE@btpn.com.id
	private String emailAddress;
	private String periodeSendForAdmin;//daily, weekly, monthly
	private int amountSendPeriodForAdmin;//1,2...
	
	//for PIC
	private String periodeSendBeforeLimit;//daily, weekly, monthly
	private int amountSendPeriodBeforeLimit;//1,2,3...
	
	private String periodeSendAfterLimit;//daily, weekly, monthly, only for PIC. Not for admin
	private int amountSendPeriodAfterLimit;//1,2,3...
	
	private String description;
	
	
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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPeriodeSendForAdmin() {
		return periodeSendForAdmin;
	}
	public void setPeriodeSendForAdmin(String periodeSendForAdmin) {
		this.periodeSendForAdmin = periodeSendForAdmin;
	}
	public int getAmountSendPeriodForAdmin() {
		return amountSendPeriodForAdmin;
	}
	public void setAmountSendPeriodForAdmin(int amountSendPeriodForAdmin) {
		this.amountSendPeriodForAdmin = amountSendPeriodForAdmin;
	}
	public String getPeriodeSendBeforeLimit() {
		return periodeSendBeforeLimit;
	}
	public void setPeriodeSendBeforeLimit(String periodeSendBeforeLimit) {
		this.periodeSendBeforeLimit = periodeSendBeforeLimit;
	}
	public int getAmountSendPeriodBeforeLimit() {
		return amountSendPeriodBeforeLimit;
	}
	public void setAmountSendPeriodBeforeLimit(int amountSendPeriodBeforeLimit) {
		this.amountSendPeriodBeforeLimit = amountSendPeriodBeforeLimit;
	}
	public String getPeriodeSendAfterLimit() {
		return periodeSendAfterLimit;
	}
	public void setPeriodeSendAfterLimit(String periodeSendAfterLimit) {
		this.periodeSendAfterLimit = periodeSendAfterLimit;
	}
	public int getAmountSendPeriodAfterLimit() {
		return amountSendPeriodAfterLimit;
	}
	public void setAmountSendPeriodAfterLimit(int amountSendPeriodAfterLimit) {
		this.amountSendPeriodAfterLimit = amountSendPeriodAfterLimit;
	}
	public long getEmailTemplateId() {
		return emailTemplateId;
	}
	public void setEmailTemplateId(long emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
