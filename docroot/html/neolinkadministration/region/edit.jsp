<%@include file="/html/init.jsp"%>

<jsp:useBean id="region" scope="request" class="com.codevergence.neolink.admin.model.Region"></jsp:useBean>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_REGION %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveRegion" var="saveURL" />

<liferay-ui:header title="edit-region"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input
	        name="id"
	        value="<%= region.getId() %>"
	        type="hidden"
	    />

		<aui:input name="region-code" value="<%= region.getRegionCode() %>" size="50%" />
		<aui:input name="region-name" value="<%= region.getName() %>" size="50%" />

		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>


