<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="region"></liferay-ui:header>

<%	
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addParameterURL = renderResponse.createRenderURL();
	addParameterURL.setParameter("tabs1", Constants.TAB_REGION);
	addParameterURL.setParameter(Constants.CMD, Constants.EDIT);
%>

<liferay-ui:icon image="add" url="<%= addParameterURL.toString() %>" label="<%= true %>" message="add-region"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="10">
	<liferay-ui:search-container-results>

	<%
	List<Region> tempResults = (List<Region>)renderRequest.getAttribute("regions");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.Region"
		keyProperty="id" 
		modelVar="region">

		<%-- <liferay-ui:search-container-column-text name="region-id" property="id" /> --%>
		<liferay-ui:search-container-column-text name="region-code" property="regionCode" />
		<liferay-ui:search-container-column-text name="region-name" property="name" />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/region/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>