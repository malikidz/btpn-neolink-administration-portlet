<%@include file="/html/init.jsp"%>

<jsp:useBean id="branchType" class="com.codevergence.neolink.admin.model.BranchType" scope="request"></jsp:useBean>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_BRANCH_TYPE %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveBranchType" var="saveURL" />

<liferay-ui:header title="edit-branch-type"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input name="id" value="${ branchType.rowId }" type="hidden" />
		<aui:input name="type-id" value="${ branchType.branchTypeId }" size="30%" />
		<aui:input name="name" value="${ branchType.branchTypeName }" size="30%" />
		<%-- <aui:input name="map-marker" value="${ branchType.branchTypeMapMarker }" size="30%" /> --%>
		<aui:input name="sandi-kantor" value="${ branchType.sandiKantor }" size="30%" />
		<aui:input name="show-order" value="${ branchType.showOrder }" size="30%" />
		
		<span class="aui-field aui-field-text"> 
			<span class="aui-field-content"> 
				<label class="aui-field-label"><liferay-ui:message key="color-code" /></label> 
					<span class="aui-field-element "> 
						<input class="aui-field-input aui-field-input-text palette"
						name="color-code"
						value="${ branchType.colorCode }" size="30%" type="text">
					</span>
			</span>
		</span>
		
		<aui:input checked="<%= FormUtils.isSelected(branchType.isPerhitunganAmi()) %>" 
		name="perhitungan-ami" type="checkbox" />
		<aui:input checked="<%= FormUtils.isSelected(branchType.isMapView()) %>" 
		name="map-view" type="checkbox" />
		
		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>
