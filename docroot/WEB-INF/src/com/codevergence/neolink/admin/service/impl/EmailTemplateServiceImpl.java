package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.EmailTemplateDao;
import com.codevergence.neolink.admin.model.EmailTemplate;
import com.codevergence.neolink.admin.service.EmailTemplateService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("emailTemplateService")
public class EmailTemplateServiceImpl implements EmailTemplateService {
	
	@Qualifier("emailTemplateDao")
	private EmailTemplateDao emailTemplateDao;
	
	public EmailTemplateDao getEmailTemplateDao() {
		return emailTemplateDao;
	}
	
	@Autowired
	public void setEmailTemplateDao(EmailTemplateDao emailTemplateDao) {
		this.emailTemplateDao = emailTemplateDao;
	}

	public List<EmailTemplate> getAll() {
		return emailTemplateDao.getAll();
	}

	public EmailTemplate getById(long id) {
		return emailTemplateDao.getById(id);
	}

	public void insert(EmailTemplate emailTemplate) {
		emailTemplateDao.insert(emailTemplate);
	}

	public void update(EmailTemplate emailTemplate) {
		emailTemplateDao.update(emailTemplate);
	}

	public void delete(long id) {
		emailTemplateDao.delete(id);
	}

}
