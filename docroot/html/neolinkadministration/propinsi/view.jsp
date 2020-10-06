<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="provinsi"></liferay-ui:header>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addProvinceUrl = renderResponse.createRenderURL();
	addProvinceUrl.setParameter("tabs1", "provinsi");
	addProvinceUrl.setParameter(Constants.CMD, Constants.EDIT);
%>
<liferay-ui:icon image="add" url="<%= addProvinceUrl.toString() %>" label="<%= true %>" message="add-province"  />

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
		<liferay-ui:search-container-column-text name="zona-bi" value="<%= propinsi.getZonaBi().getNamaZona() %>" />
		<liferay-ui:search-container-column-text name="provinsi-id" property="propinsiId" />
		<liferay-ui:search-container-column-text name="name" property="propinsiName" />
		<%-- <liferay-ui:search-container-column-text name="image-id" property="imageId" /> --%>
		<liferay-ui:search-container-column-text name="color-code" 
		value='<font color="${ propinsi.colorCode }">${ propinsi.colorCode }</font>' />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/propinsi/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>