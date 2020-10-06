<%@include file="/html/init.jsp"%>

<jsp:useBean id="lob" scope="request" class="com.codevergence.neolink.admin.model.LineOfBusiness"></jsp:useBean>
<%
List<Region> regions = (List<Region>) request.getAttribute("regions");
List<Region> masterRegions = (List<Region>) request.getAttribute("masterRegions");

%>
<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_LOB %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveLOB" var="saveURL" />


<liferay-ui:header title="edit-lob"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input
	        name="id"
	        value="<%= lob.getId() %>"
	        type="hidden"
	    />
	    
	    <aui:input name="unit-business-id" value="<%= lob.getUnitBusinessId() %>" size="30%" />
		<aui:input name="unit-business" value="<%= lob.getUnitBusiness() %>" size="30%" />
		
		<span class="aui-field aui-field-text"> 
			<span class="aui-field-content"> 
				<label class="aui-field-label"><liferay-ui:message key="color-code" /></label> 
					<span class="aui-field-element "> 
						<input class="aui-field-input aui-field-input-text palette"
						name="color-code"
						value="<%= lob.getColorCode() %>" size="30%" type="text">
					</span>
			</span>
		</span>
		<aui:input name="email-group" value="<%= lob.getEmailGroup() %>" size="30%" />
		<aui:input name="show-order" value="<%= lob.getShowOrder() %>" size="30%" />
		
		<!-- Begin list of province, unit business & region below -->
		<%-- <table>
			<tr>
				<th>NO.</th>
				<th>PROPINSI</th>
				<th>REGION</th>
			</tr>

			<c:forEach items="${ regions }" var="region" varStatus="pijet">
			<aui:input
	        name="province-mapper-id-${region.provinceId }"
	        value="${region.mappingId }"
	        type="hidden"
	    />
			<tr>
				<td>${pijet.index + 1}</td>
				<td><aui:input name="propinsi-${region.provinceName }" label="Propinsi" value="${region.provinceName }" size="50%" disabled="<%= true %>"/> </td>
				<td>
				<aui:select label="Region" name="regions${region.provinceId }">
						<aui:option label="select" value="" />
						<c:forEach items="${ masterRegions }" var="regionForProvince">
							<aui:option label="${regionForProvince.name }" value="${ regionForProvince.id }"
								selected="${ regionForProvince.id eq region.id }" />
						</c:forEach>
					</aui:select> 
				</td>
			</tr>
			</c:forEach>
		</table> --%>
		<!-- End list of province, unit business & region below -->
		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>
	</aui:fieldset>

</aui:form>
