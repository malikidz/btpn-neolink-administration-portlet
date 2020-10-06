<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="zona-bi"></liferay-ui:header>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addZonaBiURL = renderResponse.createRenderURL();
	addZonaBiURL.setParameter("tabs1", "zona-bi");
	addZonaBiURL.setParameter(Constants.CMD, Constants.EDIT);
%>
<liferay-ui:icon image="add" url="<%= addZonaBiURL.toString() %>" label="<%= true %>" message="add-zona-bi"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="10">
	<liferay-ui:search-container-results>

	<%
	List<ZonaBI> tempResults = (List<ZonaBI>)renderRequest.getAttribute("zonaBis");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.ZonaBI"
		keyProperty="id" 
		modelVar="zonaBi">

		<liferay-ui:search-container-column-text name="nama-zona" property="namaZona" />
		<liferay-ui:search-container-column-text name="color-code" 
			value='<font color="${ zonaBi.colorCode }">${ zonaBi.colorCode }</font>' />
		<liferay-ui:search-container-column-text name="koefisien" property="koefisien" />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/zonabi/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>