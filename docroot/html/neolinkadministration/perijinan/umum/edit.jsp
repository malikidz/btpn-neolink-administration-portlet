<%@include file="/html/init.jsp"%>

<jsp:useBean id="perijinanUmum" class="com.codevergence.neolink.admin.model.PerijinanUmum" scope="request"></jsp:useBean>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_PERIJINAN %>"></portlet:param>
	<portlet:param name="tabs2" value="<%= Constants.TAB_PERIJINAN_UMUM %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="savePerijinanUmum" var="saveURL" />

<liferay-ui:header title="edit-perijinan-umum"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input name="id" type="hidden" value="<%= perijinanUmum.getId() %>" />
		<aui:field-wrapper name="branch" label="branch">
			<%= perijinanUmum.getBranch().getName() %>
		</aui:field-wrapper>
		<aui:input name="name-document" value="<%= perijinanUmum.getNamaDokumen() %>" size="30%" />
		<aui:input name="no-document" value="<%= perijinanUmum.getNoDokumen() %>" size="30%" />
		
		<% 
		Date openedDate = perijinanUmum.getTglKeluarDokumen();
		Calendar cal1 = FormUtils.getCalendar(openedDate);
		%>
		<aui:field-wrapper name="keluar-dokumen-date" label="keluar-dokumen-date">
			<liferay-ui:input-date
				dayValue="<%= cal1.get(Calendar.DATE) %>" 
				dayParam="day1" 
				disabled="<%= false %>" 
				firstDayOfWeek="<%= cal1.getFirstDayOfWeek() - 1 %>"
				monthParam="month1" monthValue="<%= cal1.get(Calendar.MONTH) %>"
				yearParam="year1" yearValue="<%= cal1.get(Calendar.YEAR) %>"
				yearRangeStart="<%= cal1.get(Calendar.YEAR) - 50 %>"
				yearRangeEnd="<%= cal1.get(Calendar.YEAR) + 50 %>" 
			/>
		</aui:field-wrapper>
		
		<% 
		Date endDate = perijinanUmum.getTglBerakhirDokumen();
		Calendar cal2 = FormUtils.getCalendar(endDate);
		%>
		<aui:field-wrapper name="berakhir-dokumen-date" label="berakhir-dokumen-date">
			<liferay-ui:input-date
				dayValue="<%= cal2.get(Calendar.DATE) %>" 
				dayParam="day2" 
				disabled="<%= false %>" 
				firstDayOfWeek="<%= cal2.getFirstDayOfWeek() - 1 %>"
				monthParam="month2" monthValue="<%= cal2.get(Calendar.MONTH) %>"
				yearParam="year2" yearValue="<%= cal2.get(Calendar.YEAR) %>"
				yearRangeStart="<%= cal2.get(Calendar.YEAR) - 50 %>"
				yearRangeEnd="<%= cal2.get(Calendar.YEAR) + 50 %>" 
			/>
		</aui:field-wrapper>
		
		<aui:input checked="<%= FormUtils.isSelected(perijinanUmum.isActive()) %>" name="active" type="checkbox" />
		
		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>
</aui:form>
