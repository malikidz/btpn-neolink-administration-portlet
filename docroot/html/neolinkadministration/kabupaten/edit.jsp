<%@include file="/html/init.jsp"%>

<jsp:useBean id="kabupaten" scope="request" class="com.codevergence.neolink.admin.model.KabupatenKota"></jsp:useBean>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_KABUPATEN %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveKabupatenKota" var="saveURL" />

<liferay-ui:header title="edit-kabupaten"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input
	        name="id"
	        value="<%= kabupaten.getId() %>"
	        type="hidden"
	    />
		<aui:select name="provinsi">
			<aui:option label="select" value="" />
			<c:forEach items="${ provinces }" var="propinsi">
				<aui:option label="${ propinsi.propinsiName }" value="${ propinsi.rowId }"
					selected="${ propinsi.propinsiId eq kabupaten.provinsi.propinsiId }" />
			</c:forEach>
		</aui:select>

		<aui:input name="kabupaten-code" value="<%= kabupaten.getKabupatenId() %>" size="50%" />
		<aui:input name="kabupaten-name" value="<%= kabupaten.getName() %>" size="50%" />
		
		<aui:input checked="<%= FormUtils.isSelected(kabupaten.isActive()) %>" name="active" type="checkbox" />

		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>


