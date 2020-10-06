<%@include file="/html/init.jsp"%>

<jsp:useBean id="branchClass" class="com.codevergence.neolink.admin.model.BranchClass" scope="request"></jsp:useBean>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_BRANCH_CLASS %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveBranchClass" var="saveURL" />

<liferay-ui:header title="edit-branch-class"></liferay-ui:header>

<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">
	<aui:fieldset>
		<aui:input name="rowId" value="${ branchClass.rowId }" type="hidden" />
		<aui:input name="branchClassId" value="${ branchClass.branchClassId }" size="30%" />
		<aui:input name="branchClassName" value="${ branchClass.branchClassName }" size="30%" />
		<aui:input name="branchClassMapMarker" value="${ branchClass.branchClassMapMarker }" size="30%" />

		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>
</aui:form>
