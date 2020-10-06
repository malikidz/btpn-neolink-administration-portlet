<%@ include file="/html/init.jsp" %>

<portlet:resourceURL escapeXml="false" id="excel" var="resourceURL">
	<%-- <portlet:param name="displayStyle" value="${ display }"></portlet:param>
	<c:if test="${ not empty region}">
		<portlet:param name="form[Region][]" value="${ region }"></portlet:param>
	</c:if>
	<c:if test="${ not empty area}">
		<portlet:param name="form[Area][]" value="${ area }"></portlet:param>
	</c:if>
	<c:if test="${ not empty branch}">
		<portlet:param name="form[Branch][]" value="${ branch }"></portlet:param>
	</c:if> --%>
</portlet:resourceURL>
<a class="download_as_excel" href="${ resourceURL }">Download as excel</a>

