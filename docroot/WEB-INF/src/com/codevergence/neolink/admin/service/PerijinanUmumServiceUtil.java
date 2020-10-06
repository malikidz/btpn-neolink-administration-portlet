package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class PerijinanUmumServiceUtil {
	private static PerijinanUmumService _service;

	public static PerijinanUmumService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(request.getPortletSession().getPortletContext());
			_service =  (PerijinanUmumService)appContenxt.getBean("perijinanUmumService");
		}
		return _service;
	}
	
	public static void setService(PerijinanUmumService service) {
		PerijinanUmumServiceUtil._service = service;
	}
}
