<%@ include file="/html/init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Parameter parameter = (Parameter) row.getObject();
long groupId = themeDisplay.getLayout().getGroupId();
String name = Parameter.class.getName();
String primKey = String.valueOf(parameter.getParam());
%>

<liferay-ui:icon-menu cssClass="">
<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_ZONA_BI); 
	portletURL.setParameter("tabs1", "parameter");
	portletURL.setParameter("param", primKey);
	portletURL.setParameter(Constants.CMD, Constants.EDIT);
%>

	<liferay-ui:icon image="edit" url="<%= portletURL.toString() %>" />

</liferay-ui:icon-menu>