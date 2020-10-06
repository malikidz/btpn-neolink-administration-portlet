package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class InvestStandardBIServiceUtil {
	private static InvestStandardBIService _service;

	public static InvestStandardBIService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (InvestStandardBIService)appContenxt.getBean("investStandardBIService");
		}
		return _service;
	}

	public static void setService(InvestStandardBIService service) {
		InvestStandardBIServiceUtil._service = service;
	}
}
