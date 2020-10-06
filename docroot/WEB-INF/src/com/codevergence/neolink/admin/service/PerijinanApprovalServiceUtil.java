package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class PerijinanApprovalServiceUtil {
	private static PerijinanApprovalService _service;

	public static PerijinanApprovalService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (PerijinanApprovalService)appContenxt.getBean("perijinanApprovalService");
		}
		return _service;
	}

	public static void setService(PerijinanApprovalService service) {
		PerijinanApprovalServiceUtil._service = service;
	}
}
