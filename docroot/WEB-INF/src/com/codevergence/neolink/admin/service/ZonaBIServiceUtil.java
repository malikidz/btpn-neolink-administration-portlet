package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class ZonaBIServiceUtil {
	private static ZonaBIService _service;

	public static ZonaBIService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (ZonaBIService)appContenxt.getBean("zonaBiService");
		}
		return _service;
	}

	public static void setService(ZonaBIService service) {
		ZonaBIServiceUtil._service = service;
	}
}
