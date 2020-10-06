package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.PerijinanBIDao;
import com.codevergence.neolink.admin.model.Branch;
import com.codevergence.neolink.admin.model.PerijinanBI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("perijinanBIDao")
public class PerijinanBIDaoImpl implements PerijinanBIDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*public List<PerijinanBI> getAll(boolean isstatus, boolean isCompleteInformation, String name, String branchName) {
		List<PerijinanBI> results = new ArrayList<PerijinanBI>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_bi a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id", new PerijinanBIMapper());
		
		return results;
	}*/
	
	private static final String _BASE_SELECT_QUERY = new StringBuilder()
	.append("select a.*, b.row_id, b.branch_name from dokumen_perijinan_bi a ")
	.append("join neolink_master_branch b on a.master_branch_id = b.row_id ")
	.append("where status = ? ")
	.toString();
	
	public List<PerijinanBI> getAll(boolean isActive, boolean isCompleteInformation, String name, String branchName) {
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
	
	private List<PerijinanBI> getAll(boolean isActive, String name, String branchName) {
		List<PerijinanBI> results = new ArrayList<PerijinanBI>();
		String query = "";
		if(name == null){
			query = new StringBuilder(_BASE_SELECT_QUERY).append(" and b.branch_id = ? ").toString();
		} else {
			query = new StringBuilder(_BASE_SELECT_QUERY).append(" and a.nama_dokumen = ? and b.branch_id = ? ").toString();
		}

			/*.append("and (nomor_dokumen is null or ")
			.append("tgl_dikeluarkan_dokumen is null or ")
			.append("tgl_berakhir_dokumen is null)")*/

		if(name == null){
			results = this.jdbcTemplate.query(query, new Object[]{ isActive, branchName }, new PerijinanBIMapper());
		} else {
			results = this.jdbcTemplate.query(query, new Object[]{ isActive, name, branchName }, new PerijinanBIMapper());
		}
		
		return results;
	}
	
	private List<PerijinanBI> getAll(boolean isActive, boolean isCompleteInformation) {
		List<PerijinanBI> results = new ArrayList<PerijinanBI>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_bi a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where status = ?", new Object[]{ isActive }, new PerijinanBIMapper());
		return results;
	}
	
	private List<PerijinanBI> getAllByName(boolean isActive, boolean isCompleteInformation, String name) {
		List<PerijinanBI> results = new ArrayList<PerijinanBI>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_bi a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where status = ? and a.nama_dokumen = ?", new Object[]{isActive, name}, new PerijinanBIMapper());
		return results;
	}
	
	private List<PerijinanBI> getAllByBId(boolean isActive, boolean isCompleteInformation, String branchName) {
		List<PerijinanBI> results = new ArrayList<PerijinanBI>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_bi a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where status = ? and b.branch_id = ?", 
				new Object[]{isActive, branchName}, 
				new PerijinanBIMapper());
		return results;
	}
	
	private List<PerijinanBI> getAllByName_BId(boolean isActive, boolean isCompleteInformation, String name, String branchName) {
		List<PerijinanBI> results = new ArrayList<PerijinanBI>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_bi a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where status = ? and a.nama_dokumen = ? and b.branch_id = ?", 
				new Object[]{isActive, name, branchName}, 
				new PerijinanBIMapper());
		return results;
	}

	public PerijinanBI getById(long id) {
		List<PerijinanBI> results = new ArrayList<PerijinanBI>();
		results = this.jdbcTemplate.query("select a.*, b.row_id, b.branch_name from dokumen_perijinan_bi a " +
				"join neolink_master_branch b on a.master_branch_id = b.row_id " +
				"where a.id = ?", new Object[]{id}, new PerijinanBIMapper());
		PerijinanBI result = DataAccessUtils.singleResult(results);
		return result;
	}

	public void insert(PerijinanBI perijinanBI) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(PerijinanBI perijinanBI) {
		this.jdbcTemplate.update("UPDATE dokumen_perijinan_bi SET " +
				"nomor_dokumen = ?, tgl_dikeluarkan_dokumen = ?, tgl_berakhir_dokumen = ?, status = ?, efective = ? " +
				"WHERE id = ?", new Object[]{perijinanBI.getNoDokumen(), perijinanBI.getTglKeluarDokumen(),
				perijinanBI.getTglBerakhirDokumen(), perijinanBI.isActive(), perijinanBI.isEfective(), perijinanBI.getId()});
	}
	
	public void delete(long id) {
		this.jdbcTemplate.update("DELETE FROM dokumen_perijinan_bi WHERE id = ?", id);
	}
	
	private static final class PerijinanBIMapper implements RowMapper<PerijinanBI> {
		public PerijinanBI mapRow(ResultSet rs, int arg1) throws SQLException {
			PerijinanBI perijinanBI = new PerijinanBI();
			perijinanBI.setId(rs.getLong("id"));
			perijinanBI.setNamaDokumen(rs.getString("nama_dokumen"));
			perijinanBI.setNoDokumen(rs.getString("nomor_dokumen"));
			perijinanBI.setTglKeluarDokumen(rs.getDate("tgl_dikeluarkan_dokumen"));
			perijinanBI.setTglBerakhirDokumen(rs.getDate("tgl_berakhir_dokumen"));
			perijinanBI.setActive(rs.getBoolean("status"));
			perijinanBI.setEfective(rs.getBoolean("efective"));
			perijinanBI.setFileId(rs.getLong("file_id"));
			perijinanBI.setFolderId(rs.getLong("folder_id"));
			perijinanBI.setGroupId(rs.getLong("group_id"));
			
			Branch branch = new Branch();
			branch.setId(rs.getInt("master_branch_id"));
			branch.setName(rs.getString("branch_name"));
			perijinanBI.setBranch(branch);
			return perijinanBI;
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
		results = this.jdbcTemplate.query("select distinct b.branch_id, b.branch_name from dokumen_perijinan_bi a " +
				  "join neolink_master_branch b on a.master_branch_id = b.row_id", 
				new PerijinanBranchMapper());
		return results;
	}

	public List<String> getNameHasDocument() {
		List<String> results = new ArrayList<String>();
		results = this.jdbcTemplate.queryForList("select distinct a.nama_dokumen from dokumen_perijinan_bi a " +
				  "join neolink_master_branch b on a.master_branch_id = b.row_id", 
				String.class);
		return results;
	}

	public void toggleRecap(boolean isRecap, long id) {
		this.jdbcTemplate.update("UPDATE dokumen_perijinan_bi SET " +
				"is_recaped = ? " +
				"WHERE id = ?", new Object[]{isRecap, id});
	}

	public void toggleRecapEfective(boolean isRecapEfective, long id) {
		this.jdbcTemplate.update("UPDATE dokumen_perijinan_bi SET " +
				"is_recaped_efective = ? " +
				"WHERE id = ?", new Object[]{isRecapEfective, id});
	}

	public void updateOpeningDateMasterBranch(Date openingDate, long dokumenBiId) {
		this.jdbcTemplate.update("update Neolink_Master_Branch set OPENING_DATE = ? where ROW_ID in " +
				"(select master_branch_id from dokumen_perijinan_bi where id = ?)"
				, new Object[]{openingDate, dokumenBiId});
		
	}

}
