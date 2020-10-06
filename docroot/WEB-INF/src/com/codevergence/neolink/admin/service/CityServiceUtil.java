package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class CityServiceUtil {
	private static CityService _service;

	public static CityService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (CityService)appContenxt.getBean("cityService");
		}
		return _service;
	}

	public static void setService(CityService service) {
		CityServiceUtil._service = service;
	}
}
