package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class BranchServiceUtil {
	private static BranchService _service;

	public static BranchService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (BranchService)appContenxt.getBean("branchService");
		}
		return _service;
	}

	public static void setService(BranchService service) {
		BranchServiceUtil._service = service;
	}
}
