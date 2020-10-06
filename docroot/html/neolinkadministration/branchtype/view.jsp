<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="branch-type"></liferay-ui:header>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addBranchTypeUrl = renderResponse.createRenderURL();
	addBranchTypeUrl.setParameter("tabs1", "branch-type");
	addBranchTypeUrl.setParameter(Constants.CMD, Constants.EDIT);
%>
<liferay-ui:icon image="add" url="<%= addBranchTypeUrl.toString() %>" label="<%= true %>" message="add-branch-type"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="10">
	<liferay-ui:search-container-results>

	<%
	List<BranchType> tempResults = (List<BranchType>)renderRequest.getAttribute("branchTypes");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.BranchType"
		keyProperty="rowId" 
		modelVar="branchType">

		<%-- <liferay-ui:search-container-column-text name="branch-type-id" property="branchTypeId" /> --%>
		<liferay-ui:search-container-column-text name="name" property="branchTypeName" />
		<%-- <liferay-ui:search-container-column-text name="map-marker" property="branchTypeMapMarker" /> --%>
		<liferay-ui:search-container-column-text name="sandi-kantor" property="sandiKantor" />
		<liferay-ui:search-container-column-text name="show-order" property="showOrder" />
		<liferay-ui:search-container-column-text name="color-code" 
		value='<font color="${ branchType.colorCode }">${ branchType.colorCode }</font>' />
		<liferay-ui:search-container-column-text name="perhitungan-ami" 
		value='<%= branchType.isPerhitunganAmi() ? "Ya" : "Tidak" %>' />
		<liferay-ui:search-container-column-text name="map-view" 
		value='<%= branchType.isMapView()? "Ya" : "Tidak" %>' />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/branchtype/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>