package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class EmailSchedulerServiceUtil {
	private static EmailSchedulerService _service;

	public static EmailSchedulerService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (EmailSchedulerService)appContenxt.getBean("emailSchedulerService");
		}
		return _service;
	}

	public static void setService(EmailSchedulerService service) {
		EmailSchedulerServiceUtil._service = service;
	}
}
