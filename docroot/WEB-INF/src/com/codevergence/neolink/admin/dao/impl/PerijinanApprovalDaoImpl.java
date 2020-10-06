package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.PerijinanApprovalDao;
import com.codevergence.neolink.admin.model.PerijinanApproval;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("perijinanApprovalDao")
public class PerijinanApprovalDaoImpl implements PerijinanApprovalDao {
	private JdbcTemplate jdbcTemplate;
	private static final String BASE_QUERY_PERIJINAN_APPROVAL = "SELECT * FROM perijinan_approval WHERE file_id in (";
	private static final String KURUNG_TUTUP = ")";
	private static final String COMMA = ", ";
	private static final String INSERT_SQL = "INSERT INTO perijinan_approval (file_id, folder_id, group_id, file_name, title, user_id, approver_user_id, notes, is_approved) VALUES (?,?,?,?,?,?,?,?,?)";
	//TABLE COLUMNS
	private static final String ID = "id";
	private static final String FILE_ID = "file_id";
	private static final String FOLDER_ID = "folder_id";
	private static final String GROUP_ID = "group_id";
	private static final String FILENAME = "file_name";
	private static final String TITLE = "title";
	private static final String NOTES = "notes";
	private static final String USER_ID ="user_id" ;
	private static final String APPROVER_USER_ID = "approver_user_id";
	private static final String IS_APPROVED = "is_approved";
	private static final String UPDATED_DATE = "updated_date";
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void save(PerijinanApproval perijinanApproval) throws DataAccessException {
		jdbcTemplate.update(INSERT_SQL
				,
				new Object[] { perijinanApproval.getFileId(), perijinanApproval.getFolderId(), 
						perijinanApproval.getGroupId(), perijinanApproval.getFileName(), perijinanApproval.getTitle(), perijinanApproval.getUserId(), 
						perijinanApproval.getApprovalUserIdl(), perijinanApproval.getNotes(), false });
	}

	public List<PerijinanApproval> getPerijinanApprovals(List<Long> fileIds) throws DataAccessException {
		List<PerijinanApproval> results = new ArrayList<PerijinanApproval>();
		if(fileIds.size() > 0) {
			StringBuffer query = new StringBuffer();
			query.append(BASE_QUERY_PERIJINAN_APPROVAL);
			query.append(buildIds(fileIds));
			query.append(KURUNG_TUTUP);
			results = jdbcTemplate.query(query.toString(), new PerijinanApprovalMapper());
		}
		return results;
	}
	
	private String buildIds(List<Long> fileIds){
		StringBuffer ids = new StringBuffer();
		if(fileIds != null && fileIds.size() > 0){
			for (int i = 0; i < fileIds.size(); i++) {
				if(i > 0 && i < fileIds.size()){
					ids.append(COMMA);
				}
				ids.append(fileIds.get(i));
				
			}
		}
		return ids.toString();
	}
	private static final class PerijinanApprovalMapper implements RowMapper<PerijinanApproval> {
		public PerijinanApproval mapRow(ResultSet rs, int arg1) throws SQLException {
			PerijinanApproval perijinanApproval  = new PerijinanApproval();
			perijinanApproval.setId(rs.getLong(ID));
			perijinanApproval.setFileId(rs.getLong(FILE_ID));
			perijinanApproval.setFolderId(rs.getLong(FOLDER_ID));
			perijinanApproval.setGroupId(rs.getLong(GROUP_ID));
			perijinanApproval.setFileName(rs.getString(FILENAME));
			perijinanApproval.setTitle(rs.getString(TITLE));
			perijinanApproval.setNotes(rs.getString(NOTES));
			perijinanApproval.setUserId(rs.getLong(USER_ID));
			perijinanApproval.setApprovalUserIdl(rs.getLong(APPROVER_USER_ID));
			perijinanApproval.setApproved(rs.getBoolean(IS_APPROVED));
			perijinanApproval.setUpdatedDate(rs.getDate(UPDATED_DATE));
			return perijinanApproval;
		}
	}

}
