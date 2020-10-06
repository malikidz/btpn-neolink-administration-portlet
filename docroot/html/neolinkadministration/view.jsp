<%@ include file="/html/init.jsp" %>

<c:choose>
	<c:when test="${not empty errorMessage}">
		<div class="portlet-msg-error">${ errorMessage }</div>
	</c:when>
</c:choose>

<liferay-ui:error key="DATAISUSED" message="office-is-used"></liferay-ui:error>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_ZONA_BI);
	portletURL.setParameter("tabs1", tabs1);
%>

<liferay-ui:tabs param="tabs1" names="${ tabs }" url="<%= portletURL.toString() %>" />
	
<c:choose>
    <c:when test='<%= tabs1.equals(Constants.TAB_PARAMETER) %>'>
    	<c:choose>
    		<c:when test="${ not empty parameter }">
    			<jsp:include page="../neolinkadministration/parameter/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/parameter/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>

    <c:when test='<%= tabs1.equals(Constants.TAB_AMI_FORMULA) %>'>
        <%@ include file="../neolinkadministration/amiformula/view.jsp" %>
    </c:when>

    <c:when test='<%= tabs1.equals(Constants.TAB_ZONA_BI) %>'>
        <c:choose>
    		<c:when test="${ not empty zonaBi }">
    			<jsp:include page="../neolinkadministration/zonabi/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/zonabi/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>

    <c:when test='<%= tabs1.equals(Constants.TAB_EMAIL_TEMPLATE) %>'>
        <c:choose>
    		<c:when test="${ not empty emailTemplate }">
    			<jsp:include page="../neolinkadministration/emailtemplate/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/emailtemplate/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>

    <c:when test='<%= tabs1.equals(Constants.TAB_LOB) %>'>
        <c:choose>
    		<c:when test="${ not empty lob }">
    			<jsp:include page="../neolinkadministration/lob/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/lob/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_BRANCH_TYPE) %>'>
        <c:choose>
    		<c:when test="${ not empty branchType }">
    			<jsp:include page="../neolinkadministration/branchtype/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/branchtype/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_BRANCH_CLASS) %>'>
        <c:choose>
    		<c:when test="${ not empty branchClass }">
    			<jsp:include page="../neolinkadministration/branchclass/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/branchclass/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
	<c:when test='<%= tabs1.equals(Constants.TAB_INVEST_STANDARD_BI) %>'>
        <c:choose>
    		<c:when test="${ not empty investStandardBI }">
    			<jsp:include page="../neolinkadministration/investandardbi/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/investandardbi/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
     <c:when test='<%= tabs1.equals(Constants.TAB_PROVINCE) %>'>
        <c:choose>
    		<c:when test="${ not empty province }">
    			<jsp:include page="../neolinkadministration/propinsi/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/propinsi/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
     <c:when test='<%= tabs1.equals(Constants.TAB_EMAIL_SCHEDULER) %>'>
        <c:choose>
    		<c:when test="${ not empty emailScheduler }">
    			<jsp:include page="../neolinkadministration/emailscheduler/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/emailscheduler/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_REGION) %>'>
        <c:choose>
    		<c:when test="${ not empty region }">
    			<jsp:include page="../neolinkadministration/region/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/region/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_PERIJINAN) %>'>
        <jsp:include page="../neolinkadministration/perijinan/view.jsp"></jsp:include>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_KABUPATEN) %>'>
        <c:choose>
    		<c:when test="${ not empty kabupaten }">
    			<jsp:include page="../neolinkadministration/kabupaten/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/kabupaten/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_REGION_MAPPING) %>'>
        <c:choose>
    		<c:when test="${ not empty province }">
    			<jsp:include page="../neolinkadministration/regionmapping/edit.jsp"></jsp:include>
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../neolinkadministration/regionmapping/view.jsp"></jsp:include>
    		</c:otherwise>
    	</c:choose>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_EXCEL_REPORT) %>'>
        <jsp:include page="../neolinkadministration/excel-report/view.jsp"></jsp:include>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_UPLOAD_APPROVAL) %>'>
        <jsp:include page="../neolinkadministration/upload-approval/view.jsp"></jsp:include>
    </c:when>
    
    <c:when test='<%= tabs1.equals(Constants.TAB_UPLOAD_REVISE) %>'>
        <jsp:include page="../neolinkadministration/upload-revise/view.jsp"></jsp:include>
    </c:when>
    
</c:choose>

<script>
jQuery(function() {
    jQuery(".palette").spectrum({
    	preferredFormat: "hex",
    	showPaletteOnly: true,
        showPalette:true,
        showButtons: false,
        palette: [
                  ["#ffffff", "#000000", "#c00000", "#f79646", "#f5f445", "#7fd13b", "#4bacc6", "#1f497d", "#8064a2", "#ff0000"],
                  ["#f2f2f2", "#7f7f7f", "#f8d1d3", "#fdeada", "#fafdd7", "#e5f5d7", "#dbeef3", "#c6d9f0", "#e5e0ec", "#ffcc00"],
                  ["#d7d7d7", "#595959", "#f2a3a7", "#fbd5b5", "#fbfaae", "#cbecb0", "#b7dde8", "#8db3e2", "#ccc1d9", "#ffff00"],
                  ["#bebebe", "#414141", "#eb757b", "#fac08f", "#eef98e", "#b2e389", "#92cddc", "#548dd4", "#b2a2c7", "#00ff00"],
                  ["#a3a3a3", "#2a2a2a", "#a3171e", "#e36c09", "#dede07", "#5ea226", "#31859b", "#17365d", "#5f497a", "#0000ff"],
                  ["#7e7e7e", "#141414", "#6d0f14", "#974806", "#c0c00d", "#3f6c19", "#205867", "#0f243e", "#3f3151", "#9900ff"]
              ]
    });
    
});
</script>

<p>
	<font color="#f7f7f7">Last Build 20150119</font>
</p>