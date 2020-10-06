<%@include file="/html/init.jsp"%>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_PARAMETER %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveDataParameter" var="saveURL" />


<liferay-ui:error key="UPDATEERROR"
	message="Failed updating parameter, please try again." />

<liferay-ui:header title="edit-parameter"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>

		<aui:input name="param" value="${ parameter.param }" size="30%" />
		<aui:input name="value" value="${ parameter.value }" size="30%" />

		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>
