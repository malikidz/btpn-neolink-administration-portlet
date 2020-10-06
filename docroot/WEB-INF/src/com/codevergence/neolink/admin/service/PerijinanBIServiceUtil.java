package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class PerijinanBIServiceUtil {
	private static PerijinanBIService _service;

	public static PerijinanBIService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(request.getPortletSession().getPortletContext());
			_service =  (PerijinanBIService)appContenxt.getBean("perijinanBIService");
		}
		return _service;
	}
	
	public static void setService(PerijinanBIService service) {
		PerijinanBIServiceUtil._service = service;
	}
}
