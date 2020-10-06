<%@include file="/html/init.jsp"%>

<jsp:useBean id="investStandardBI" scope="request" class="com.codevergence.neolink.admin.model.InvestStandardBI"></jsp:useBean>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_INVEST_STANDARD_BI %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveInvestStandardBI" var="saveURL" />

<liferay-ui:header title="edit-invest-standard-bi"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input name="id" value="<%= investStandardBI.getId() %>" type="hidden" />
		<aui:input name="name" value="<%= investStandardBI.getName() %>" size="30%" />
		<aui:input name="book-type" value="<%= investStandardBI.getBookType() %>" size="30%" />
		<aui:select name="branch-type">
			<option value="">Please select</option>
			<c:forEach items="${ branchTypes }" var="branchType">
				<aui:option selected="${ branchType.rowId eq investStandardBI.branchType.rowId }" 
					label="(${branchType.branchTypeId}) ${ branchType.branchTypeName }" 
					value="${ branchType.rowId }" />
			</c:forEach>
		</aui:select>
		<aui:input name="invest-value" value="${ investStandardBI.investValue }" size="30%" />

		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>
