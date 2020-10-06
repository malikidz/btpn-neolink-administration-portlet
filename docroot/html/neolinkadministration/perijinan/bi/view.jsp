<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="perijinan-bi"></liferay-ui:header>

<jsp:useBean id="isActive" type="java.lang.Boolean" scope="request"></jsp:useBean>
<jsp:useBean id="isCompleteInformation" type="java.lang.Boolean" scope="request"></jsp:useBean>
<jsp:useBean id="name" type="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="branchName" type="java.lang.String" scope="request"></jsp:useBean>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_PERIJINAN);
	String tabs2 = ParamUtil.getString(renderRequest, "tabs2", Constants.TAB_PERIJINAN_BI);
	portletURL.setParameter("tabs1", tabs1);
	portletURL.setParameter("tabs2", tabs2);
	portletURL.setParameter("isActive", isActive.toString());
	portletURL.setParameter("isCompleteInformation", isCompleteInformation.toString());
	portletURL.setParameter("name", name);
	portletURL.setParameter("branchName", branchName);

	PortletURL filterURL = renderResponse.createRenderURL();
	filterURL.setParameter("tabs1", Constants.TAB_PERIJINAN);
	filterURL.setParameter("tabs2", Constants.TAB_PERIJINAN_BI);

%>

<aui:form action="<%= filterURL %>" method="post">
	<aui:fieldset>
		<aui:select name="branch">
			<aui:option label="Select"></aui:option>
			<c:forEach items="${ branchs }" var="branch">
				<aui:option value="${ branch.branchId }" label="${ branch.branchId } - ${ branch.name }"></aui:option>
			</c:forEach>
		</aui:select>
		<aui:select name="name-document">
			<aui:option label="Select"></aui:option>
			<c:forEach items="${ names }" var="branch">
				<aui:option value="${ branch }" label="${ branch }"></aui:option>
			</c:forEach>
		</aui:select>
		<%-- <aui:input checked="<%= FormUtils.isSelected(false) %>" name="show-active" type="checkbox" /> --%>
		<aui:input checked="<%= FormUtils.isSelected(isCompleteInformation) %>" name="show-complete-active" type="checkbox" />
		<aui:button-row>
			<aui:button type="submit" value="Filter" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>
<br>

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="10">
	<liferay-ui:search-container-results>

	<%
	List<PerijinanBI> tempResults = (List<PerijinanBI>)renderRequest.getAttribute("perijinanbis");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.PerijinanBI"
		keyProperty="id" 
		modelVar="model">
		
		<liferay-ui:search-container-column-text name="branch" property="branch.name" />
		<liferay-ui:search-container-column-text name="name" property="namaDokumen" />
		<liferay-ui:search-container-column-text name="no-document" property="noDokumen" />
		<liferay-ui:search-container-column-text name="keluar-dokumen-date" property="tglKeluarDokumen" />
		<liferay-ui:search-container-column-text name="operasi-dokumen-date" property="tglBerakhirDokumen" />
		<liferay-ui:search-container-column-text name="active" value='<%= model.isActive() ? "active" : "inactive" %>' />

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/perijinan/bi/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>