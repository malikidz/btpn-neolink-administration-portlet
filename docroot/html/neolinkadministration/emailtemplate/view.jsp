<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="email-template"></liferay-ui:header>

<%	
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addParameterURL = renderResponse.createRenderURL();
	addParameterURL.setParameter("tabs1", Constants.TAB_EMAIL_TEMPLATE);
	addParameterURL.setParameter(Constants.CMD, Constants.EDIT);
%>

<liferay-ui:icon image="add" url="<%= addParameterURL.toString() %>" label="<%= true %>" message="add-email-template"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="10">
	<liferay-ui:search-container-results>

	<%
	List<EmailTemplate> tempResults = (List<EmailTemplate>)renderRequest.getAttribute("emailTemplates");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.EmailTemplate"
		keyProperty="id" 
		modelVar="emailTemplate">

		<liferay-ui:search-container-column-text name="template-name" property="name" />
		<liferay-ui:search-container-column-text name="template-subject" property="subject" />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/emailtemplate/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>