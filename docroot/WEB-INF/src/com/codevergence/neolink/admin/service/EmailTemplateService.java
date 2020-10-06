package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.EmailTemplate;

import java.util.List;

public interface EmailTemplateService {
	public List<EmailTemplate> getAll();
	public EmailTemplate getById(long id);
	public void insert(EmailTemplate emailTemplate);
	public void update(EmailTemplate emailTemplate);
	public void delete(long id);
}
