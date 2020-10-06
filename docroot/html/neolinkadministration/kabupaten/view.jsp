<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="kabupaten"></liferay-ui:header>

<%	
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addParameterURL = renderResponse.createRenderURL();
	addParameterURL.setParameter("tabs1", Constants.TAB_KABUPATEN);
	addParameterURL.setParameter(Constants.CMD, Constants.EDIT);
	
	List<KabupatenKota> tempResults = (List<KabupatenKota>)renderRequest.getAttribute("kabupatens");
%>

<liferay-ui:icon image="add" url="<%= addParameterURL.toString() %>" label="<%= true %>" message="add-kabupaten"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="<%=tempResults.size() %>">
	<liferay-ui:search-container-results>

	<%
	
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.KabupatenKota"
		keyProperty="id" 
		modelVar="kabupaten">

		<%-- <liferay-ui:search-container-column-text name="region-id" property="id" /> --%>
		<liferay-ui:search-container-column-text name="kabupaten-code" property="kabupatenId" />
		<liferay-ui:search-container-column-text name="kabupaten-name" property="name" />
		<liferay-ui:search-container-column-text name="propinsi-code" property="provinsi.propinsiId" />
		<liferay-ui:search-container-column-text name="propinsi-name" property="provinsi.propinsiName" />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/kabupaten/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>

<%@ include file="/html/download_as_excel.jsp" %>