package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class BranchTypeServiceUtil {
	private static BranchTypeService _service;

	public static BranchTypeService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (BranchTypeService)appContenxt.getBean("branchTypeService");
		}
		return _service;
	}

	public static void setService(BranchTypeService service) {
		BranchTypeServiceUtil._service = service;
	}
}
