<%@ include file="/html/init.jsp" %>

<c:choose>
	<c:when test="${not empty errorMessage}">
		<div class="portlet-msg-error">${ errorMessage }</div>
	</c:when>
</c:choose>

<liferay-ui:error key="DATAISUSED" message="office-is-used"></liferay-ui:error>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_PERIJINAN);
	String tabs2 = ParamUtil.getString(renderRequest, "tabs2", Constants.TAB_PERIJINAN_UMUM);
	portletURL.setParameter("tabs1", tabs1);
	portletURL.setParameter("tabs2", tabs2);
%>

<liferay-ui:tabs param="tabs2" names="perijinan-umum,perijinan-bi" url="<%= portletURL.toString() %>" />

<c:choose>
    <c:when test='<%= tabs2.equals(Constants.TAB_PERIJINAN_UMUM) %>'>
    	<c:choose>
    		<c:when test="${ not empty perijinanUmum }">
    			<jsp:include page="../../neolinkadministration/perijinan/umum/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../../neolinkadministration/perijinan/umum/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>

    <c:when test='<%= tabs2.equals(Constants.TAB_PERIJINAN_BI) %>'>
        <c:choose>
    		<c:when test="${ not empty perijinanBI }">
    			<jsp:include page="../../neolinkadministration/perijinan/bi/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../../neolinkadministration/perijinan/bi/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
</c:choose>