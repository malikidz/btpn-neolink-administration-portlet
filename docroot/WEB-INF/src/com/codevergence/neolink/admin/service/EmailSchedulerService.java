package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.EmailScheduler;

import java.util.Hashtable;
import java.util.List;

public interface EmailSchedulerService {
	public void insert(EmailScheduler emailScheduler);
	public void update(EmailScheduler emailScheduler);
	public void delete(long rowId);
	
	public EmailScheduler withId(long id);
	public List<EmailScheduler> getAll();
	public Hashtable<Long, EmailScheduler> getAllAsHash();
}
