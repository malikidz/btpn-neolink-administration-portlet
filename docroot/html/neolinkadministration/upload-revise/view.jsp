<%@ include file="/html/init.jsp" %>

<jsp:useBean id="fileReviseList" type="java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry>" scope="request"></jsp:useBean>
<jsp:useBean id="pApprovals" type="java.util.List<com.codevergence.neolink.admin.model.PerijinanApproval>" scope="request"></jsp:useBean>

<liferay-ui:header title="upload-revise"></liferay-ui:header>

<div class="results-grid"> 
	<table class="taglib-search-iterator"> 
		<tbody>
			<tr class="portlet-section-header results-header"> 
				<th class="first"><liferay-ui:message key="filename" /></th>
				<th class="last"><liferay-ui:message key="status" /></th>
				<th class="last"><liferay-ui:message key="comment" /></th> 
			</tr>
			<%
				int counter = 0;
				for(int i = 0; i < fileReviseList.size(); i++){
					DLFileEntry fileEntry = fileReviseList.get(i);
					%>
					<tr class='portlet-section-alternate results-row <c:if test="<%= i % 2 == 1 %>">alt</c:if>'> 
						<td class="first">
							<a href="<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH + HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())) %>" target="_blank"><%= fileEntry.getTitle() %></a>
						</td>
						<td><liferay-ui:message key="revised" /></td>
						<%
						if(counter < pApprovals.size() && pApprovals.get(counter).getFileId() == fileEntry.getFileEntryId()){
							%>
							<td class="last"><%= pApprovals.get(counter).getNotes() %></td>
							<%
							counter++;
						}else{
							%>
							<td class="last"></td>
							<%
						}
						%>
					</tr>
					<%
				}
			%>
		</tbody>
	</table>
	
	<p>
		Showing ${ fileReviseListCount } items
	</p>
	
</div>