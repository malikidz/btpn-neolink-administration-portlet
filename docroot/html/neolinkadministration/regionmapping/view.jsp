<%@ include file="/html/init.jsp" %>

<jsp:useBean id="lineOfBusiness" scope="request" class="com.codevergence.neolink.admin.model.LineOfBusiness"></jsp:useBean>
<liferay-ui:header title="region-mapping"></liferay-ui:header>

<%	
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_REGION_MAPPING);
	String tabs2 = ParamUtil.getString(renderRequest, "tabs2", lineOfBusiness.getUnitBusinessId());
	if(tabs1 != null){
		portletURL.setParameter("tabs1", tabs1);
	}
	
	if(tabs2 != null){
		portletURL.setParameter("tabs2", tabs2);
	}
	
%>

<liferay-ui:tabs param="tabs2" names="${ tabs2 }" url="<%= portletURL.toString() %>" />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="35">
	<liferay-ui:search-container-results>

	<%
	List<Province> tempResults = (List<Province>)renderRequest.getAttribute("provinces");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.Province"
		keyProperty="rowId" modelVar="propinsi">

		<%-- <liferay-ui:search-container-column-text name="province-row-id" property="rowId" /> --%>
		<liferay-ui:search-container-column-text name="provinsi-id" property="propinsiId" />
		<liferay-ui:search-container-column-text name="name" property="propinsiName" />
		<%-- <liferay-ui:search-container-column-text name="image-id" property="imageId" /> --%>

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/regionmapping/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>