<%@ include file="/html/init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
KabupatenKota parameter = (KabupatenKota) row.getObject();
long groupId = themeDisplay.getLayout().getGroupId();
String name = KabupatenKota.class.getName();
String primKey = String.valueOf(parameter.getId());
%>

<liferay-ui:icon-menu cssClass="">
<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_ZONA_BI); 
	portletURL.setParameter("tabs1", Constants.TAB_KABUPATEN);
	portletURL.setParameter("id", primKey);
	portletURL.setParameter("cmd", Constants.EDIT);
%>

	<liferay-ui:icon image="edit" url="<%= portletURL.toString() %>" />
	
	<c:choose>
		<c:when test="<%= parameter.isActive() %>">
			<portlet:actionURL name="toggleActiveKabupaten" var="updateStatusURL">
			  <portlet:param name="id" value="<%= primKey %>" />
			  <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DEACTIVATE %>"></portlet:param>
			</portlet:actionURL>
		    <liferay-ui:icon-deactivate url="<%= updateStatusURL.toString() %>"></liferay-ui:icon-deactivate>
		</c:when>
		<c:otherwise>
			<portlet:actionURL name="toggleActiveKabupaten" var="updateStatusURL">
			  <portlet:param name="id" value="<%= primKey %>" />
			  <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>"></portlet:param>
			</portlet:actionURL>
		    <liferay-ui:icon image="activate" url="<%= updateStatusURL.toString() %>"></liferay-ui:icon>
		</c:otherwise>
	</c:choose>

</liferay-ui:icon-menu>