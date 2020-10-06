package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.ExcelReportDao;
import com.codevergence.neolink.admin.model.ExcelReport;
import com.codevergence.neolink.admin.service.ExcelReportService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("excelReportService")
public class ExcelReportServiceImpl implements ExcelReportService {

	@Qualifier("excelReportDap")
	private ExcelReportDao excelReportDao;
	
	public void clearTable() {
		this.excelReportDao.clearTable();
		
	}

	
	public void insert(ExcelReport excelReport) {
		this.excelReportDao.insert(excelReport);
		
	}

	
	public List<ExcelReport> getAll() {
		return this.excelReportDao.getAll();
	}


	public ExcelReportDao getExcelReportDao() {
		return excelReportDao;
	}


	@Autowired
	public void setExcelReportDao(ExcelReportDao excelReportDao) {
		this.excelReportDao = excelReportDao;
	}

}
