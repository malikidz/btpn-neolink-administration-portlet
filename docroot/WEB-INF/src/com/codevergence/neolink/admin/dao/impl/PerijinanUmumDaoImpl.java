package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.PerijinanUmumDao;
import com.codevergence.neolink.admin.model.Branch;
import com.codevergence.neolink.admin.model.PerijinanUmum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("perijinanUmumDao")
public class PerijinanUmumDaoImpl implements PerijinanUmumDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static final String _BASE_SELECT_QUERY = new StringBuilder()
	.append("select a.*, b.row_id, b.branch_name from dokumen_perijinan_umum a ")
	.append("join neolink_master_branch b on a.master_branch_id = b.row_id ")
	.append("where status = ? ")
	.toString();

	public List<PerijinanUmum> getAll(boolean isActive, boolean isCompleteInformation, String name, String branchName) {
		/*if(name != null && branchName != null && isCompleteInformation){
			return getAllByName_BId(isActive, isCompleteInformation, name, branchName);
		}else if(name != null && isCompleteInformation){
			return getAllByName(isActive, isCompleteInformation, name);
		}else if(branchName != null && isCompleteInformation){
			return getAllByBId(isActive, isCompleteInformation, branchName);
		}else if(isCompleteInformation){
			return getAll(isActive, isCompleteInformation);
		}else{
			return getAll(isActive, name, branchName);
		}
		*/
		if(name != null && branchName != null && isCompleteInformation){
			return getAllByName_BId(isActive, isCompleteInformation, name, branchName);
		}else if(name != null && isCompleteInformation){
			return getAllByName(isActive, isCompleteInformation, name);
		}else if(branchName != null && isCompleteInformation){
			return getAllByBId(isActive, isCompleteInformation, branchName);
		}else if(isCompleteInformation){
			return getAll(isActive, isCompleteInformation);
		}else{
			return getAll(isCompleteInformation, name, branchName);
		}
	}
	
	private List<PerijinanUmum> getAll(boolean isActive, String name, String branchName) {
		List<PerijinanUmum> results = new ArrayList<PerijinanUmum>();
		String query = new StringBuilder(_BASE_SELECT_QUERY)
			.append(" and a.nama_dokumen = ? and b.branch_id = ? ")
			/*.append("and (nomor_dokumen is null or ")
			.append("tgl_dikeluarkan_dokumen is null or ")
			.append("tgl_berakhir_dokumen is null)")*/
			.toString();
		results = this.jdbcTemplate.query(query, new Object[]{isActive, name, branchName}, new PerijinanUmumMapper());
		return results;
	}
	
	private List<PerijinanUmum> getAll(boolean isActive, boolean isCompleteInformation) {
		List<PerijinanUmum> results = new ArrayList<PerijinanUmum>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_umum a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where status = ?", new Object[]{isActive}, new PerijinanUmumMapper());
		return results;
	}
	
	private List<PerijinanUmum> getAllByName(boolean isActive, boolean isCompleteInformation, String name) {
		List<PerijinanUmum> results = new ArrayList<PerijinanUmum>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_umum a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where status = ? and a.nama_dokumen = ?", new Object[]{isActive, name}, new PerijinanUmumMapper());
		return results;
	}
	
	private List<PerijinanUmum> getAllByBId(boolean isActive, boolean isCompleteInformation, String branchName) {
		List<PerijinanUmum> results = new ArrayList<PerijinanUmum>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_umum a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where status = ? and b.branch_id = ?", 
				new Object[]{isActive, branchName}, 
				new PerijinanUmumMapper());
		return results;
	}
	
	private List<PerijinanUmum> getAllByName_BId(boolean isActive, boolean isCompleteInformation, String name, String branchName) {
		List<PerijinanUmum> results = new ArrayList<PerijinanUmum>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_umum a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where status = ? and a.nama_dokumen = ? and b.branch_id = ?", 
				new Object[]{isActive, name, branchName}, 
				new PerijinanUmumMapper());
		return results;
	}

	public PerijinanUmum getById(long id) {
		List<PerijinanUmum> results = new ArrayList<PerijinanUmum>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_umum a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where a.id = ?", new Object[]{id}, new PerijinanUmumMapper());
		PerijinanUmum result = DataAccessUtils.singleResult(results);
		return result;
	}

	public void insert(PerijinanUmum perijinanUmum) {
		this.jdbcTemplate.update("INSERT INTO dokumen_perijinan_umum " +
				"(nomor_dokumen, tgl_dikeluarkan_dokumen, tgl_berakhir_dokumen, status) values " +
				"(?,?,?,?) WHERE id = ?", new Object[]{perijinanUmum.getNoDokumen(), perijinanUmum.getTglKeluarDokumen(),
				perijinanUmum.getTglBerakhirDokumen(), perijinanUmum.isActive(), perijinanUmum.getId()});
		
	}
	
	public void update(PerijinanUmum perijinanUmum) {
		this.jdbcTemplate.update("UPDATE dokumen_perijinan_umum SET " +
				"nomor_dokumen = ?, tgl_dikeluarkan_dokumen = ?, tgl_berakhir_dokumen = ?, status = ? " +
				"WHERE id = ?", new Object[]{perijinanUmum.getNoDokumen(), perijinanUmum.getTglKeluarDokumen(),
				perijinanUmum.getTglBerakhirDokumen(), perijinanUmum.isActive(), perijinanUmum.getId()});
	}

	public void delete(long id) {
		this.jdbcTemplate.update("DELETE FROM dokumen_perijinan_umum WHERE id = ?", id);
	}
	
	private static final class PerijinanUmumMapper implements RowMapper<PerijinanUmum> {
		public PerijinanUmum mapRow(ResultSet rs, int arg1) throws SQLException {
			PerijinanUmum perijinanUmum = new PerijinanUmum();
			perijinanUmum.setId(rs.getLong("id"));
			perijinanUmum.setNamaDokumen(rs.getString("nama_dokumen"));
			perijinanUmum.setNoDokumen(rs.getString("nomor_dokumen"));
			perijinanUmum.setTglKeluarDokumen(rs.getDate("tgl_dikeluarkan_dokumen"));
			perijinanUmum.setTglBerakhirDokumen(rs.getDate("tgl_berakhir_dokumen"));
			perijinanUmum.setActive(rs.getBoolean("status"));
			perijinanUmum.setFileId(rs.getLong("file_id"));
			perijinanUmum.setFolderId(rs.getLong("folder_id"));
			perijinanUmum.setGroupId(rs.getLong("group_id"));
			
			Branch branch = new Branch();
			branch.setId(rs.getInt("master_branch_id"));
			branch.setName(rs.getString("branch_name"));
			perijinanUmum.setBranch(branch);
			return perijinanUmum;
		}
	}
	
	private static final class PerijinanBranchMapper implements RowMapper<Branch> {
		public Branch mapRow(ResultSet rs, int arg1) throws SQLException {
			Branch branch = new Branch();
			branch.setBranchId(rs.getString("branch_id"));
			branch.setName(rs.getString("branch_name"));
			return branch;
		}
	}

	public List<Branch> getBranchHasDocument() {
		List<Branch> results = new ArrayList<Branch>();
		results = this.jdbcTemplate.query("select distinct b.branch_id, b.branch_name from dokumen_perijinan_umum a " +
				  "join neolink_master_branch b on a.master_branch_id = b.row_id", 
				new PerijinanBranchMapper());
		return results;
	}

	public List<String> getNameHasDocument() {
		List<String> results = new ArrayList<String>();
		results = this.jdbcTemplate.queryForList("select distinct a.nama_dokumen from dokumen_perijinan_umum a " +
				  "join neolink_master_branch b on a.master_branch_id = b.row_id", 
				String.class);
		return results;
	}

}
