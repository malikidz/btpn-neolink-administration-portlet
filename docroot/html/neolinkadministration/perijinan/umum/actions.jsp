<%@ include file="/html/init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
PerijinanUmum parameter = (PerijinanUmum) row.getObject();
long groupId = themeDisplay.getLayout().getGroupId();
String name = PerijinanUmum.class.getName();
String primKey = String.valueOf(parameter.getId());
%>

<liferay-ui:icon-menu cssClass="">
	<%
		PortletURL portletURL = renderResponse.createRenderURL();
		String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_ZONA_BI); 
		portletURL.setParameter("tabs1", Constants.TAB_PERIJINAN);
		portletURL.setParameter("tabs2", Constants.TAB_PERIJINAN_UMUM);
		portletURL.setParameter("id", primKey);
		portletURL.setParameter(Constants.CMD, Constants.EDIT);
	%>
	<liferay-ui:icon image="edit" url="<%= portletURL.toString() %>" />

	<%
		PortletURL deleteURL = renderResponse.createActionURL();
		deleteURL.setParameter("id", primKey);
		deleteURL.setParameter("javax.portlet.action", "deletePerijinanUmum");
	%>
	<liferay-ui:icon-delete url="<%= deleteURL.toString() %>"></liferay-ui:icon-delete>
</liferay-ui:icon-menu>