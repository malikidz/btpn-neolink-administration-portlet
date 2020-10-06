package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class ProvinceServiceUtil {

	private static ProvinceService _service;

	public static ProvinceService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(request.getPortletSession().getPortletContext());
			_service =  (ProvinceService)appContenxt.getBean("provinceService");
		}
		return _service;
	}

	public static void setService(ProvinceService service) {
		ProvinceServiceUtil._service = service;
	}
	
}
