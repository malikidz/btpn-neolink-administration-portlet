package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.PerijinanApproval;

import java.util.List;

public interface PerijinanApprovalService {
	public void save(PerijinanApproval perijinanApproval);
	public List<PerijinanApproval> getPerijinanApprovals(List<Long> fileIds);
}
