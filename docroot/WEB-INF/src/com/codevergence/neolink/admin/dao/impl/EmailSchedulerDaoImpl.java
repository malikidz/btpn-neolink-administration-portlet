package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.EmailSchedulerDao;
import com.codevergence.neolink.admin.model.EmailScheduler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("emailSchedulerDao")
public class EmailSchedulerDaoImpl implements EmailSchedulerDao {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void insert(EmailScheduler emailScheduler)
			throws DataAccessException {
		jdbcTemplate.update(
				"INSERT INTO email_scheduler (" +
				"name, " +
				"email_template_id, " +
				"admin_email_address, " +
				"admin_period_send, " +
				"admin_amount_period, " +
				"pic_period_send_before_limit, " +
				"pic_amount_period_before_limit ," +
				"pic_period_send_after_limit, " +
				"pic_amount_period_after_limit, " +
				"keterangan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {
						emailScheduler.getName(),
						emailScheduler.getEmailTemplateId(), 
						emailScheduler.getEmailAddress(), 
						emailScheduler.getPeriodeSendForAdmin(), 
						emailScheduler.getAmountSendPeriodForAdmin(), 
						emailScheduler.getPeriodeSendBeforeLimit(), 
						emailScheduler.getAmountSendPeriodBeforeLimit(), 
						emailScheduler.getPeriodeSendAfterLimit(), 
						emailScheduler.getAmountSendPeriodAfterLimit(), 
						emailScheduler.getDescription() });
		
		
	}

	public void update(EmailScheduler emailScheduler)
			throws DataAccessException {
		jdbcTemplate.update(
				"UPDATE email_scheduler SET name = ?, email_template_id= ?, admin_email_address= ?, admin_period_send= ?, admin_amount_period= ?, pic_period_send_before_limit= ?, pic_amount_period_before_limit = ?,pic_period_send_after_limit= ?, pic_amount_period_after_limit= ?, keterangan = ? WHERE id = ?",
				new Object[] { 
						emailScheduler.getName(),
						emailScheduler.getEmailTemplateId(), 
						emailScheduler.getEmailAddress(), 
						emailScheduler.getPeriodeSendForAdmin(), 
						emailScheduler.getAmountSendPeriodForAdmin(), 
						emailScheduler.getPeriodeSendBeforeLimit(), 
						emailScheduler.getAmountSendPeriodBeforeLimit(), 
						emailScheduler.getPeriodeSendAfterLimit(), 
						emailScheduler.getAmountSendPeriodAfterLimit(), 
						emailScheduler.getDescription(), 
						emailScheduler.getId() });
		
	}

	public void delete(long rowId) throws DataAccessException {
		this.jdbcTemplate.update("DELETE FROM email_scheduler where id = ?", rowId);
		
	}

	public EmailScheduler withId(long id) throws DataAccessException {
		List<EmailScheduler> results = new ArrayList<EmailScheduler>();
		results  = jdbcTemplate.query("SELECT * FROM email_scheduler WHERE id = ?",
				new Object[] { id }, new EmailSchedulerMapper());
		EmailScheduler result = DataAccessUtils.singleResult(results);
		return result;
	}

	public List<EmailScheduler> getAll() throws DataAccessException {
		List<EmailScheduler> results = new ArrayList<EmailScheduler>();
		results  = jdbcTemplate.query("SELECT * FROM email_scheduler order by id",
				new EmailSchedulerMapper());
		return results;
	}

	public Hashtable<Long, EmailScheduler> getAllAsHash()
			throws DataAccessException {
		List<EmailScheduler> results = new ArrayList<EmailScheduler>();
		Hashtable<Long, EmailScheduler> resultHash = new Hashtable<Long, EmailScheduler>();
		
		results  = this.getAll();
		for (int i = 0; i < results.size(); i++) {
			EmailScheduler emailScheduler  = results.get(i);
			resultHash.put(new Long(emailScheduler.getId()), emailScheduler);
		}
		return resultHash;
	}
	
	private static final class EmailSchedulerMapper implements RowMapper<EmailScheduler> {
		public EmailScheduler mapRow(ResultSet rs, int arg1) throws SQLException {
			EmailScheduler emailScheduler = new EmailScheduler();
			emailScheduler.setId(rs.getLong("id"));
			emailScheduler.setName(rs.getString("name"));
			emailScheduler.setEmailTemplateId(rs.getLong("email_template_id"));
			emailScheduler.setEmailAddress(rs.getString("admin_email_address"));
			emailScheduler.setPeriodeSendForAdmin(rs.getString("admin_period_send"));
			emailScheduler.setAmountSendPeriodForAdmin(rs.getInt("admin_amount_period"));
			emailScheduler.setPeriodeSendBeforeLimit(rs.getString("pic_period_send_before_limit"));
			emailScheduler.setAmountSendPeriodBeforeLimit(rs.getInt("pic_amount_period_before_limit"));
			emailScheduler.setPeriodeSendAfterLimit(rs.getString("pic_period_send_after_limit"));
			emailScheduler.setAmountSendPeriodAfterLimit(rs.getInt("pic_amount_period_after_limit"));
			emailScheduler.setDescription(rs.getString("keterangan"));
			return emailScheduler;
		}
	}

}
