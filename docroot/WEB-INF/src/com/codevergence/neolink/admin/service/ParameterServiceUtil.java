package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class ParameterServiceUtil {
	private static ParameterService _service;

	public static ParameterService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (ParameterService)appContenxt.getBean("parameterService");
		}
		return _service;
	}

	public static void setService(ParameterService service) {
		ParameterServiceUtil._service = service;
	}
}
