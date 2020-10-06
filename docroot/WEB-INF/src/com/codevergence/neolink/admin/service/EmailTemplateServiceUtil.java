package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class EmailTemplateServiceUtil {
	private static EmailTemplateService _service;

	public static EmailTemplateService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (EmailTemplateService)appContenxt.getBean("emailTemplateService");
		}
		return _service;
	}

	public static void setService(EmailTemplateService service) {
		EmailTemplateServiceUtil._service = service;
	}
}
