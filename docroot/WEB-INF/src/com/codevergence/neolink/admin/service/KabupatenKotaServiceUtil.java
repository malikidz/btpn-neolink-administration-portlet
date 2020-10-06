package com.codevergence.neolink.admin.service;

import javax.portlet.PortletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

public class KabupatenKotaServiceUtil {
	private static KabupatenKotaService _service;

	public static KabupatenKotaService getService(PortletRequest request) {
		if(_service == null){
			ApplicationContext appContenxt = PortletApplicationContextUtils.getWebApplicationContext(
					request.getPortletSession().getPortletContext());
			_service =  (KabupatenKotaService)appContenxt.getBean("kabupatenKotaService");
		}
		return _service;
	}
	
	public static void setService(KabupatenKotaService service) {
		KabupatenKotaServiceUtil._service = service;
	}
}
