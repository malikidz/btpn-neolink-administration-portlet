package com.codevergence.neolink.admin.dao;

import com.codevergence.neolink.admin.model.PerijinanApproval;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface PerijinanApprovalDao {
	public void save(PerijinanApproval perijinanApproval) throws DataAccessException;
	public List<PerijinanApproval> getPerijinanApprovals(List<Long> fileIds) throws DataAccessException;
}
