<%@include file="/html/init.jsp"%>

<jsp:useBean id="emailTemplate" scope="request" class="com.codevergence.neolink.admin.model.EmailTemplate"></jsp:useBean>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_EMAIL_TEMPLATE %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveEmailTemplate" var="saveURL" />

<liferay-ui:header title="edit-email-template"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input
	        name="id"
	        value="<%= emailTemplate.getId() %>"
	        type="hidden"
	    />

		<aui:input name="template-name" value="<%= emailTemplate.getName() %>" size="50%" />
		<aui:input name="template-subject" value="<%= emailTemplate.getSubject() %>" size="60%" />
		<aui:field-wrapper label="template-body">
			<liferay-ui:input-editor name='<%= renderResponse.getNamespace() + "template-body" %>' editorImpl="<%= Constants.EDITOR_WYSIWYG_IMPL_KEY %>" toolbarSet="liferay-article" onChangeMethod='<%= renderResponse.getNamespace() + "editorContentChanged" %>' width="100%" />
		</aui:field-wrapper>

		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(emailTemplate.getBody()) %>";
	}
</aui:script>

