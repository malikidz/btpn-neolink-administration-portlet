package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.EmailTemplateDao;
import com.codevergence.neolink.admin.model.EmailTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("emailTemplateDao")
public class EmailTemplateDaoImpl implements EmailTemplateDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<EmailTemplate> getAll() throws DataAccessException {
		List<EmailTemplate> results = new ArrayList<EmailTemplate>();
		results  = jdbcTemplate.query("SELECT * FROM Neolink_Email_Template",
				new EmailTemplateMapper());
		return results;
	}

	public EmailTemplate getById(long id) throws DataAccessException {
		List<EmailTemplate> results = new ArrayList<EmailTemplate>();
		results  = jdbcTemplate.query("SELECT * FROM Neolink_Email_Template WHERE id = ?",
				new Object[] { id }, new EmailTemplateMapper());
		EmailTemplate result = DataAccessUtils.singleResult(results);
		return result;
	}

	public void insert(EmailTemplate emailTemplate) throws DataAccessException {
		jdbcTemplate.update(
				"INSERT INTO Neolink_Email_Template (nama_template, email_subject, template_body) VALUES (?, ?, ?)",
				new Object[] { emailTemplate.getName(), emailTemplate.getSubject(), emailTemplate.getBody() });
	}

	public void update(EmailTemplate emailTemplate) throws DataAccessException {
		jdbcTemplate.update(
				"UPDATE Neolink_Email_Template SET nama_template = ?, email_subject = ?, template_body = ? WHERE id = ?",
				new Object[] { emailTemplate.getName(), emailTemplate.getSubject(), emailTemplate.getBody(), emailTemplate.getId() });
	}

	public void delete(long id) throws DataAccessException {
		this.jdbcTemplate.update("DELETE FROM Neolink_Email_Template where id = ?", id);
	}
	
	private static final class EmailTemplateMapper implements RowMapper<EmailTemplate> {
		public EmailTemplate mapRow(ResultSet rs, int arg1) throws SQLException {
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setId(rs.getLong("id"));
			emailTemplate.setName(rs.getString("nama_template"));
			emailTemplate.setSubject(rs.getString("email_subject"));
			emailTemplate.setBody(rs.getString("template_body"));
			return emailTemplate;
		}
	}
}
