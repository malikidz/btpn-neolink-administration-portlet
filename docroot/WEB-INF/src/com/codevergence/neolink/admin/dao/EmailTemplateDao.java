package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.EmailTemplate;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EmailTemplateDao {
	public List<EmailTemplate> getAll() throws DataAccessException;
	public EmailTemplate getById(long id) throws DataAccessException;
	public void insert(EmailTemplate emailTemplate) throws DataAccessException;
	public void update(EmailTemplate emailTemplate) throws DataAccessException;
	public void delete(long id) throws DataAccessException;
}
