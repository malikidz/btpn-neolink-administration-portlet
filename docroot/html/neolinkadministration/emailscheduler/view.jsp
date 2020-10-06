<%@ include file="/html/init.jsp" %>

<liferay-ui:header title="email-scheduler"></liferay-ui:header>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "parameter");
	portletURL.setParameter("tabs1", tabs1);

	PortletURL addEmailSchedulerUrl = renderResponse.createRenderURL();
	addEmailSchedulerUrl.setParameter("tabs1", "email-scheduler");
	addEmailSchedulerUrl.setParameter(Constants.CMD, Constants.EDIT);
%>
<liferay-ui:icon image="add" url="<%= addEmailSchedulerUrl.toString() %>" label="<%= true %>" message="add-email-scheduler"  />

<liferay-ui:search-container iteratorURL="<%= portletURL %>"
	emptyResultsMessage="no-record" delta="35">
	<liferay-ui:search-container-results>

	<%
	List<EmailScheduler> tempResults = (List<EmailScheduler>)renderRequest.getAttribute("emailSchedulers");
  	results = ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());
  	total = tempResults.size();

  	pageContext.setAttribute("results", results);
  	pageContext.setAttribute("total", total);
  	%>

	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row
		className="com.codevergence.neolink.admin.model.EmailScheduler"
		keyProperty="id" 
		modelVar="emailScheduler"
		>

		<liferay-ui:search-container-column-text name="name" property="name" />
		<liferay-ui:search-container-column-text name="email-template" property="emailTemplateId" />
		<liferay-ui:search-container-column-text name="admin-email-address" property="emailAddress" />
		<%-- <liferay-ui:search-container-column-text name="admin-periode" property="periodeSendForAdmin" />
		<liferay-ui:search-container-column-text name="admin-amount-periode" property="amountSendPeriodForAdmin" />
		<liferay-ui:search-container-column-text name="pic-send-before-limit" property="periodeSendBeforeLimit" />
		<liferay-ui:search-container-column-text name="pic-amount-before-limit" property="amountSendPeriodBeforeLimit" />
		<liferay-ui:search-container-column-text name="pic-send-after-limit" property="periodeSendAfterLimit" />
		<liferay-ui:search-container-column-text name="pic-send-after-limit" property="amountSendPeriodAfterLimit" />
		<liferay-ui:search-container-column-text name="keterangan" property="description" />
 --%>

		<liferay-ui:search-container-column-jsp path="/html/neolinkadministration/emailscheduler/actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>