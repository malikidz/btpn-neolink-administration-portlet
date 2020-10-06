package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.EmailScheduler;

import java.util.Hashtable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EmailSchedulerDao {
	public void insert(EmailScheduler emailScheduler) throws DataAccessException;
	public void update(EmailScheduler emailScheduler) throws DataAccessException;
	public void delete(long rowId) throws DataAccessException;
	
	public EmailScheduler withId(long id) throws DataAccessException;
	public List<EmailScheduler> getAll() throws DataAccessException;
	public Hashtable<Long, EmailScheduler> getAllAsHash() throws DataAccessException;
}
