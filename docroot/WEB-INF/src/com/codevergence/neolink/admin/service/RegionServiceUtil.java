package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class RegionServiceUtil {
	private static RegionService _service;

	public static RegionService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (RegionService)appContenxt.getBean("regionService");
		}
		return _service;
	}

	public static void setService(RegionService service) {
		RegionServiceUtil._service = service;
	}
}
