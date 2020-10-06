package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.EmailSchedulerDao;
import com.codevergence.neolink.admin.model.EmailScheduler;
import com.codevergence.neolink.admin.service.EmailSchedulerService;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("emailSchedulerService")
public class EmailSchedulerServiceImpl implements EmailSchedulerService{

	@Qualifier("emailSchedulerDao")
	private EmailSchedulerDao emailSchedulerDao ;
	
	public void insert(EmailScheduler emailScheduler) {
		emailSchedulerDao.insert(emailScheduler);
		
	}

	public void update(EmailScheduler emailScheduler) {
		emailSchedulerDao.update(emailScheduler);
		
	}

	public void delete(long rowId) {
		emailSchedulerDao.delete(rowId);
		
	}

	public EmailScheduler withId(long id) {
		return emailSchedulerDao.withId(id);
	}

	public List<EmailScheduler> getAll() {
		return emailSchedulerDao.getAll();
	}

	public Hashtable<Long, EmailScheduler> getAllAsHash() {
		return emailSchedulerDao.getAllAsHash();
	}

	public EmailSchedulerDao getEmailSchedulerDao() {
		return emailSchedulerDao;
	}

	@Autowired
	public void setEmailSchedulerDao(EmailSchedulerDao emailSchedulerDao) {
		this.emailSchedulerDao = emailSchedulerDao;
	}

}
