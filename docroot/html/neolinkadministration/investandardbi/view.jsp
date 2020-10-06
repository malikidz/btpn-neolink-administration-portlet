<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="invest-standard-bi"></liferay-ui:header>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addInvestStandardBITypeUrl = renderResponse.createRenderURL();
	addInvestStandardBITypeUrl.setParameter("tabs1", "invest-standard-bi");
	addInvestStandardBITypeUrl.setParameter(Constants.CMD, Constants.EDIT);
%>
<liferay-ui:icon image="add" url="<%= addInvestStandardBITypeUrl.toString() %>" label="<%= true %>" message="add-invest-standard-bi"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="10">
	<liferay-ui:search-container-results>

	<%
	List<InvestStandardBI> tempResults = (List<InvestStandardBI>)renderRequest.getAttribute("investStandardBIs");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.InvestStandardBI"
		keyProperty="id" 
		modelVar="investStandardBI">
		<%-- <liferay-ui:search-container-column-text name="invest-standard-bi-id" property="id" /> --%>
		<liferay-ui:search-container-column-text name="branch-type" property="branchType.branchTypeName" />
		<liferay-ui:search-container-column-text name="name" property="name" />
		<liferay-ui:search-container-column-text name="book-type" property="bookType" />
		<liferay-ui:search-container-column-text name="invest-value" property="investValue" />
		

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/investandardbi/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>