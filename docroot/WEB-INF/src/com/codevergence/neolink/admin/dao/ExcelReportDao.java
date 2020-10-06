package com.codevergence.neolink.admin.dao;


import com.codevergence.neolink.admin.model.ExcelReport;

import java.util.List;

public interface ExcelReportDao {

	public void clearTable();
	public void insert(ExcelReport excelReport);
	public List<ExcelReport> getAll();
}
