<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="parameter"></liferay-ui:header>

<%	
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addParameterURL = renderResponse.createRenderURL();
	addParameterURL.setParameter("tabs1", "parameter");
	addParameterURL.setParameter(Constants.CMD, Constants.EDIT);
%>
<liferay-ui:icon image="add" url="<%= addParameterURL.toString() %>" label="<%= true %>" message="add-parameter"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="30">
	<liferay-ui:search-container-results>

	<%
	List<Parameter> tempResults = (List<Parameter>)renderRequest.getAttribute("parameters");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.Parameter"
		keyProperty="param" 
		modelVar="parameter">

		<liferay-ui:search-container-column-text name="param" property="param" />
		<liferay-ui:search-container-column-text name="value" property="value" />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/parameter/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>