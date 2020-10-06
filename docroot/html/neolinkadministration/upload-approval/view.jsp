<%@ include file="/html/init.jsp" %>

<jsp:useBean id="fileApprovalList" type="java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry>" scope="request"></jsp:useBean>

<liferay-ui:header title="upload-approval"></liferay-ui:header>

<%-- <portlet:renderURL n="reviseApproval" var="reviseURL" /> --%>
<%-- <portlet:actionURL name="approveApproval" var="approveURL" />
<portlet:actionURL name="reviseApproval" var="reviseURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_ZONA_BI %>"></portlet:param>
</portlet:actionURL> --%>

<script>
function test(value) {
	document.getElementById('a').setAttribute('value', value);
	document.getElementById('<portlet:namespace />fm').submit();
}

function toggle(source) {
	checkboxes = document.getElementsByName('fileId');
	for(var i=0, n=checkboxes.length;i<n;i++) {
		checkboxes[i].checked = source.checked;
	}
}
</script>

<%-- <aui:form name="fm" action="<%= approveURL.toString() %>" method="post"> --%>
<aui:form id="fm" name="fm" action="<%= renderResponse.createRenderURL() %>" method="post">
	<input type="hidden" name="param" value="" id="a">
	<input type="hidden" name="tabs1" value="<%= Constants.TAB_UPLOAD_APPROVAL %>">
	<aui:fieldset>
		<div class="results-grid"> 
			<table class="taglib-search-iterator"> 
				<tbody>
					<tr class="portlet-section-header results-header"> 
						<th class="first"><input type="checkbox" name="fileId" onclick="toggle(this);"></th> 
						<th class="last"><liferay-ui:message key="filename" /></th>
						<th class="last"><liferay-ui:message key="revise-comment" /></th>
					</tr>
					
					<%
						for(int i = 0; i < fileApprovalList.size(); i++){
							DLFileEntry fileEntry = fileApprovalList.get(i);
							%>
							<tr class='portlet-section-alternate results-row <c:if test="<%= i % 2 == 1 %>">alt</c:if>'> 
								<td class="first"><input type="checkbox" name="fileId" value="<%= fileEntry.getGroupId() + "|" + fileEntry.getFolderId() + "|" + fileEntry.getName() + "|" + i %>">
								</td>
								<td>
									<a href="<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH + HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())) %>" target="_blank"><%= fileEntry.getTitle() %></a>
								</td>
								<td class="last">
									<textarea rows="2" cols="70" name="comment<%= i %>" maxlength="300"></textarea>
								</td>
							</tr>
							<%
						}
					%>
				</tbody>
			</table>
		
		<p>
			Showing ${ fileApprovalListCount } items
		</p>
		
		<aui:button-row>
			<aui:button value="Approve" name="param" onclick="test('approve');"></aui:button>
			<aui:button value="Revise" name="param" onclick="test('revise');"></aui:button>
		</aui:button-row>

		</div>
	</aui:fieldset>
</aui:form>
