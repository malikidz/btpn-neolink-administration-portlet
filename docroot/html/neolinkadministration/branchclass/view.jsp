<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="branch-class"></liferay-ui:header>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addBranchClassUrl = renderResponse.createRenderURL();
	addBranchClassUrl.setParameter("tabs1", "branch-class");
	addBranchClassUrl.setParameter(Constants.CMD, Constants.EDIT);
%>
<liferay-ui:icon image="add" url="<%= addBranchClassUrl.toString() %>" label="<%= true %>" message="add-branch-class"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>" emptyResultsMessage="no-record" delta="10">
	<liferay-ui:search-container-results>

	<%
	List<BranchClass> tempResults = (List<BranchClass>)renderRequest.getAttribute("branchClasses");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row className="com.codevergence.neolink.admin.model.BranchClass"
		keyProperty="rowId" modelVar="branchClass">

		<liferay-ui:search-container-column-text name="branchClassId" property="branchClassId" />
		<liferay-ui:search-container-column-text name="branchClassName" property="branchClassName" />
		<liferay-ui:search-container-column-text name="branchClassMapMarker" property="branchClassMapMarker" />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/branchclass/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>