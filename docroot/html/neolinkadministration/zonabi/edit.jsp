<%@include file="/html/init.jsp"%>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_ZONA_BI %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveZonaBI" var="saveURL" />

<liferay-ui:header title="edit-zona-bi"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input name="id" type="hidden" value="${ zonaBi.id }" />
		<aui:input name="nama-zona" value="${ zonaBi.namaZona }" size="30%" />
		<span class="aui-field aui-field-text"> 
			<span class="aui-field-content"> 
				<label class="aui-field-label"><liferay-ui:message key="color-code" /></label> 
					<span class="aui-field-element "> 
						<input class="aui-field-input aui-field-input-text palette"
						name="color-code"
						value="${ zonaBi.colorCode }" size="30%" type="text">
					</span>
			</span>
		</span>
		<aui:input name="koefisien" value="${ zonaBi.koefisien }" size="30%" />
		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>
</aui:form>
