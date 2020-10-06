<%@ include file="/html/init.jsp" %>

<jsp:useBean id="lineOfBusiness" scope="request" class="com.codevergence.neolink.admin.model.LineOfBusiness"></jsp:useBean>
<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Province parameter = (Province) row.getObject();
long groupId = themeDisplay.getLayout().getGroupId();
String name = Province.class.getName();
String primKey = String.valueOf(parameter.getRowId());
%>

<liferay-ui:icon-menu cssClass="">
	<%
		PortletURL portletURL = renderResponse.createRenderURL();
		String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_REGION_MAPPING);
		String tabs2 = ParamUtil.getString(renderRequest, "tabs2", lineOfBusiness.getUnitBusinessId());
		portletURL.setParameter("tabs1", Constants.TAB_REGION_MAPPING);
		portletURL.setParameter("tabs2", tabs2);
		portletURL.setParameter("id", primKey);
		portletURL.setParameter("unit-business-id", String.valueOf(lineOfBusiness.getUnitBusinessId()));
		portletURL.setParameter(Constants.CMD, Constants.EDIT);
	%>
	<liferay-ui:icon image="edit" message="edit-mapping" url="<%= portletURL.toString() %>" />

	<%
		PortletURL deleteURL = renderResponse.createActionURL();
		deleteURL.setParameter("tabs2", tabs2);
		deleteURL.setParameter("id", primKey);
		deleteURL.setParameter("unit-business-id", String.valueOf(lineOfBusiness.getUnitBusinessId()));
		deleteURL.setParameter("javax.portlet.action", "deleteRegionMapping");
	%>
	<liferay-ui:icon-delete label="delete-existing-mapping" confirmation="confirmation-delete-existing-mapping" url="<%= deleteURL.toString() %>"></liferay-ui:icon-delete>
</liferay-ui:icon-menu>