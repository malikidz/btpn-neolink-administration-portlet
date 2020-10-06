<%@include file="/html/init.jsp"%>

<jsp:useBean id="lineOfBusiness" scope="request" class="com.codevergence.neolink.admin.model.LineOfBusiness"></jsp:useBean>
<jsp:useBean id="province" scope="request" class="com.codevergence.neolink.admin.model.Province"></jsp:useBean>
<jsp:useBean id="regions" scope="request" type="java.util.List<com.codevergence.neolink.admin.model.Region>"></jsp:useBean>
<jsp:useBean id="mappings" scope="request" type="java.util.List<com.codevergence.neolink.admin.model.RegionMapping>"></jsp:useBean>

<%	
	//LineOfBusiness lob = (LineOfBusiness)renderRequest.getAttribute("lob");

	PortletURL portletURL = renderResponse.createRenderURL();
	String tabs1 = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_REGION_MAPPING);
	String tabs2 = ParamUtil.getString(renderRequest, "tabs2");
	portletURL.setParameter("tabs1", tabs1);
	portletURL.setParameter("tabs2", tabs2);
%>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_REGION_MAPPING %>"></portlet:param>
	<portlet:param name="tabs2" value="<%= tabs2 %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveRegionMapping" var="saveURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>"></portlet:param>
</portlet:actionURL>

<liferay-ui:header 
	title='<%= LanguageUtil.format(pageContext, "edit-region-mapping", new String[]{lineOfBusiness.getUnitBusiness(), province.getPropinsiName()}, false) %>'
	backURL="<%= portletURL.toString() %>">
</liferay-ui:header>

<liferay-ui:tabs param="tabs2" names="${ tabs2 }" url="<%= portletURL.toString() %>" />

<aui:form name="fm" action="<%= saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input
	        name="province-id"
	        value="<%= province.getPropinsiId() %>"
	        type="hidden"
	    />
	    <aui:input
	        name="unit-business-id"
	        value="<%= lineOfBusiness.getUnitBusinessId() %>"
	        type="hidden"
	    />
		<aui:button-row>
			<aui:button value="update-associations" type="submit"></aui:button>
		</aui:button-row>
		<div class="results-grid"> 
			<table class="taglib-search-iterator"> 
				<tbody>
					<tr class="portlet-section-header results-header"> 
						<th class="first"><input type="checkbox" onclick="toggle(this);"></th> 
						<th class="last"><liferay-ui:message key="name" /></th>
					</tr>
					
					<%
						int counter = 0;
						for(int i = 0; i < regions.size(); i++){
							Region region = regions.get(i);
							%>
							<tr class='portlet-section-alternate results-row <c:if test="<%= i % 2 == 1 %>">alt</c:if>'> 
								<td class="first">
								<%
								if(counter < mappings.size() && mappings.get(counter).getId() == region.getId()){
								%>
									<input checked="checked" type="checkbox" name="region-id" value="<%= region.getId() %>">
								<%
								counter++;
								}else{
									%>
									<input type="checkbox" name="region-id" value="<%= region.getId() %>">
									<%
								}
								%>
								</td>
								<td class="last">
									<%= region.getName() %>
								</td>
							</tr>
							<%
						}
					%>
				</tbody>
			</table>
		</div>
	</aui:fieldset>
</aui:form>

<script>
function toggle(source) {
	checkboxes = document.getElementsByName('region-id');
	for(var i=0, n=checkboxes.length;i<n;i++) {
		checkboxes[i].checked = source.checked;
	}
}
</script>