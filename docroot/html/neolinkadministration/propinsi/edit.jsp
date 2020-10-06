<%@include file="/html/init.jsp"%>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_PROVINCE %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveProvince" var="saveURL" />

<liferay-ui:header title="edit-provinsi"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input name="id" value="${ province.rowId }" type="hidden" />
		<aui:select name="zona-bi">
			<aui:option label="select" value="" />
			<c:forEach items="${ zonaBis }" var="zona">
				<aui:option label="${ zona.namaZona }" value="${ zona.id }"
					selected="${ zona.id eq province.zonaBiId }" />
			</c:forEach>
		</aui:select>
		
		<aui:input name="provinsi-id" value="${ province.propinsiId }" size="30%" />
		<aui:input name="name" value="${ province.propinsiName}" size="30%" />
		<%-- <aui:input name="image-id" value="${ province.imageId }" size="30%" /> --%>
		
		<span class="aui-field aui-field-text"> 
			<span class="aui-field-content"> 
				<label class="aui-field-label"><liferay-ui:message key="color-code" /></label> 
					<span class="aui-field-element "> 
						<input class="aui-field-input aui-field-input-text palette"
						name="color-code"
						value="${ province.colorCode }" size="30%" type="text">
					</span>
			</span>
		</span>
		
		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>