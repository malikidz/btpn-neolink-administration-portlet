package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class BranchClassServiceUtil {
	private static BranchClassService _service;

	public static BranchClassService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (BranchClassService)appContenxt.getBean("branchClassService");
		}
		return _service;
	}

	public static void setService(BranchClassService service) {
		BranchClassServiceUtil._service = service;
	}
}
