package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.BranchDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("branchDao")
public class BranchDaoImpl implements BranchDao{

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static final String UPDATE_UNIT_BUSINESS =
			"update Neolink_Master_Branch set UNIT_BUSINESS_ID = ? where UNIT_BUSINESS_ID = ?";
	public void updateUnitBusinessId(String oldBusinessId, String newBusinessId) {
		jdbcTemplate.update(
				UPDATE_UNIT_BUSINESS,
				new Object[] {newBusinessId, oldBusinessId});
		
	}

	private static final String UPDATE_PROPINSI = 
			"update Neolink_Master_Branch set PROPINSI_ID = ?, 	PROPINSI_NAME = ? where PROPINSI_NAME = ?";
	public void updatePropinsi(String oldPropinsi, String newPropinsi, String newPropinsiId) {
		jdbcTemplate.update(
				UPDATE_PROPINSI,
				new Object[] {newPropinsiId, newPropinsi, oldPropinsi});
		
	}

	private static final String UPDATE_BRANCH_CLASS = 
			"update Neolink_Master_Branch set BRANCH_CLASS_ID = ? where BRANCH_CLASS_ID = ?";
	public void updateBranchClass(String oldBranchClass, String newBranchClass) {
		jdbcTemplate.update(
				UPDATE_BRANCH_CLASS,
				new Object[] {newBranchClass, oldBranchClass});
		
	}

	private static final String UPDATE_BRANCH_TYPE = 
			"update Neolink_Master_Branch set BRANCH_TYPE_ID = ? where BRANCH_TYPE_ID = ?";
	public void updateBranchType(String oldBranchTypeId, String newBranchTypeId) {
		jdbcTemplate.update(
				UPDATE_BRANCH_TYPE,
				new Object[] {newBranchTypeId, oldBranchTypeId});
		
	}

	private static final String UPDATE_REGION = 
			"update Neolink_Master_Propinsi set REGION = ? where REGION = ?";
	public void updateRegion(String oldRegion, String newRegion) {
		jdbcTemplate.update(
				UPDATE_REGION,
				new Object[] {newRegion, oldRegion});
		
	}

}
