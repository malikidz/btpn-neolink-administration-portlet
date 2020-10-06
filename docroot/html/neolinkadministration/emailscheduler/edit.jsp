<%@include file="/html/init.jsp"%>

<jsp:useBean id="emailScheduler" scope="request" class="com.codevergence.neolink.admin.model.EmailScheduler"></jsp:useBean>

<portlet:renderURL var="cancelURL">
	<portlet:param name="tabs1" value="<%= Constants.TAB_EMAIL_SCHEDULER %>"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="saveEmailScheduler" var="saveURL" />

<liferay-ui:header title="edit-email-scheduler"></liferay-ui:header>
<aui:form name="fm" action="<%=saveURL.toString()%>" method="post">

	<aui:fieldset>
		<aui:input name="id" value="<%= emailScheduler.getId() %>" type="hidden" />
		<aui:input name="name" value="${emailScheduler.name }"  size="30%" />
		<aui:select name="email-template">
			<aui:option label="select" value="" />
			<c:forEach items="${ emailTemplates }" var="emailTemplate">
				<aui:option label="${ emailTemplate.name }" value="${ emailTemplate.id }"
					selected="${ emailTemplate.id eq emailScheduler.emailTemplateId }" />
			</c:forEach>
		</aui:select>

		<aui:input name="admin-email-address" value="${emailScheduler.emailAddress }"  size="30%" />
		
		<aui:select name="admin-periode">
			<aui:option label="select" value="" />
			<aui:option label="<%= Constants.PERIOD_DAILY %>" value="<%= Constants.PERIOD_DAILY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendForAdmin(), Constants.PERIOD_DAILY) %>" />
			<aui:option label="<%= Constants.PERIOD_WEEKLY %>" value="<%= Constants.PERIOD_WEEKLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendForAdmin(), Constants.PERIOD_WEEKLY) %>" />
			<aui:option label="<%= Constants.PERIOD_MONTHLY %>" value="<%= Constants.PERIOD_MONTHLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendForAdmin(), Constants.PERIOD_MONTHLY) %>" />
			<aui:option label="<%= Constants.PERIOD_YEARLY %>" value="<%= Constants.PERIOD_YEARLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendForAdmin(), Constants.PERIOD_YEARLY) %>" />
		</aui:select>
		
		<aui:select name="admin-amount-periode">
			<aui:option label="select" value="" />
			<c:forEach begin="1" end="12" var="loop">
				<aui:option label="${ loop }" value="${ loop }" selected="${ loop eq emailScheduler.amountSendPeriodForAdmin }" />
			</c:forEach>
		</aui:select>
		
		<aui:select name="pic-send-before-limit">
			<aui:option label="select" value="" />
			<aui:option label="<%= Constants.PERIOD_DAILY %>" value="<%= Constants.PERIOD_DAILY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendBeforeLimit(), Constants.PERIOD_DAILY) %>" />
			<aui:option label="<%= Constants.PERIOD_WEEKLY %>" value="<%= Constants.PERIOD_WEEKLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendBeforeLimit(), Constants.PERIOD_WEEKLY) %>" />
			<aui:option label="<%= Constants.PERIOD_MONTHLY %>" value="<%= Constants.PERIOD_MONTHLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendBeforeLimit(), Constants.PERIOD_MONTHLY) %>" />
			<aui:option label="<%= Constants.PERIOD_YEARLY %>" value="<%= Constants.PERIOD_YEARLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendBeforeLimit(), Constants.PERIOD_YEARLY) %>" />
		</aui:select>

		<aui:select name="pic-amount-before-limit">
			<aui:option label="select" value="" />
			<c:forEach begin="1" end="12" var="loop">
				<aui:option label="${ loop }" value="${ loop }" selected="${ loop eq emailScheduler.amountSendPeriodBeforeLimit }" />
			</c:forEach>
		</aui:select>
		
		<aui:select name="pic-send-after-limit">
			<aui:option label="select" value="" />
			<aui:option label="<%= Constants.PERIOD_DAILY %>" value="<%= Constants.PERIOD_DAILY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendAfterLimit(), Constants.PERIOD_DAILY) %>" />
			<aui:option label="<%= Constants.PERIOD_WEEKLY %>" value="<%= Constants.PERIOD_WEEKLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendAfterLimit(), Constants.PERIOD_WEEKLY) %>" />
			<aui:option label="<%= Constants.PERIOD_MONTHLY %>" value="<%= Constants.PERIOD_MONTHLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendAfterLimit(), Constants.PERIOD_MONTHLY) %>" />
			<aui:option label="<%= Constants.PERIOD_YEARLY %>" value="<%= Constants.PERIOD_YEARLY %>" 
				selected="<%= FormUtils.isSelected(emailScheduler.getPeriodeSendAfterLimit(), Constants.PERIOD_YEARLY) %>" />
		</aui:select>

		<aui:select name="pic-amount-after-limit">
			<aui:option label="select" value="" />
			<c:forEach begin="1" end="12" var="loop">
				<aui:option label="${ loop }" value="${ loop }" selected="${ loop eq emailScheduler.amountSendPeriodAfterLimit }" />
			</c:forEach>
		</aui:select>
		
		<aui:field-wrapper name="keterangan">
		<textarea cols="80" rows="5" name="keterangan">${ emailScheduler.description }</textarea>
		</aui:field-wrapper>
		
		<aui:button-row>
			<aui:button type="submit" />
			<aui:button type="cancel" value="Cancel" onClick="<%= cancelURL.toString() %>" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>
