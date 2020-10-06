package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class LineOfBusinessServiceUtil {
	private static LineOfBusinessService _service;

	public static LineOfBusinessService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (LineOfBusinessService)appContenxt.getBean("lineOfBusinessService");
		}
		return _service;
	}

	public static void setService(LineOfBusinessService service) {
		LineOfBusinessServiceUtil._service = service;
	}
}
