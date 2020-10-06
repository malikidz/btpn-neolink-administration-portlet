package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.ExcelReport;

import java.util.List;

public interface ExcelReportService {

	public void clearTable();
	public void insert(ExcelReport excelReport);
	public List<ExcelReport> getAll();
	
}
