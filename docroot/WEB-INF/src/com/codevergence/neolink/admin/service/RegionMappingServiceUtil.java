package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class RegionMappingServiceUtil {

	private static RegionMappingService _service;

	public static RegionMappingService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(request.getPortletSession().getPortletContext());
			_service =  (RegionMappingService)appContenxt.getBean("regionMappingService");
		}
		return _service;
	}

	public static void setService(RegionMappingService service) {
		RegionMappingServiceUtil._service = service;
	}
	
}
