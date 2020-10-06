<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="lob"></liferay-ui:header>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addLobURL = renderResponse.createRenderURL();
	addLobURL.setParameter("tabs1", "lob");
	addLobURL.setParameter(Constants.CMD, Constants.EDIT);
%>
<liferay-ui:icon image="add" url="<%= addLobURL.toString() %>" label="<%= true %>" message="add-lob"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="10">
	<liferay-ui:search-container-results>

	<%
	List<LineOfBusiness> tempResults = (List<LineOfBusiness>)renderRequest.getAttribute("lobs");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.LineOfBusiness"
		keyProperty="id" 
		modelVar="lob">

		<liferay-ui:search-container-column-text name="unit-business-id" property="unitBusinessId" />
		<liferay-ui:search-container-column-text name="unit-business" property="unitBusiness" />
		<liferay-ui:search-container-column-text name="color-code" 
			value='<font color="${ lob.colorCode }">${ lob.colorCode }</font>' />
		<liferay-ui:search-container-column-text name="email-group" property="emailGroup" />
		<liferay-ui:search-container-column-text name="show-order" property="showOrder" />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/lob/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>