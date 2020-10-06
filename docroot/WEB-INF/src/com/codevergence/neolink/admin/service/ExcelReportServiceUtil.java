package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class ExcelReportServiceUtil {

	private static ExcelReportService _service;


	public static ExcelReportService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (ExcelReportService)appContenxt.getBean("excelReportService");
		}
		return _service;
	}

	public static void setService(ExcelReportService service) {
		ExcelReportServiceUtil._service = service;
	}
	
}
