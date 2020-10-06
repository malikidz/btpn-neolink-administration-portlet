<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="excel-report"></liferay-ui:header>

<%	
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addParameterURL = renderResponse.createRenderURL();
	addParameterURL.setParameter("tabs1", Constants.TAB_KABUPATEN);
	addParameterURL.setParameter(Constants.CMD, Constants.EDIT);
%>

<!-- liferay-ui:icon image="add" url="<%= addParameterURL.toString() %>" label="<%= true %>" message="add-kabupaten"  / -->
<portlet:resourceURL escapeXml="false" id="excel_error_report" var="resourceURL">
</portlet:resourceURL>
<a class="download_as_excel" href="${ resourceURL }">Download as excel</a>
<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="100">
	<liferay-ui:search-container-results>

	<%
	List<ExcelReport> tempResults = (List<ExcelReport>)renderRequest.getAttribute("excelreports");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.ExcelReport"
		keyProperty="rowId" 
		modelVar="excelReport">

		<liferay-ui:search-container-column-text name="excel-report-branch-id" property="branchId" />
		<liferay-ui:search-container-column-text name="excel-report-parent-id" property="parentId" />
		<liferay-ui:search-container-column-text name="excel-report-branch-class-id" property="branchClassId" />
		
		<liferay-ui:search-container-column-text name="excel-report-kabupatenkota" property="kabupatenKota" />
		<liferay-ui:search-container-column-text name="excel-report-propinsi" property="propinsi" />

		
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>

<a class="download_as_excel" href="${ resourceURL }">Download as excel</a>