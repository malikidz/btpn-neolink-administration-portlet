package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.PerijinanApprovalDao;
import com.codevergence.neolink.admin.model.PerijinanApproval;
import com.codevergence.neolink.admin.service.PerijinanApprovalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("perijinanApprovalService")
public class PerijinanApprovalServiceImpl implements PerijinanApprovalService {
	
	@Qualifier("perijinanApprovalDao")
	private PerijinanApprovalDao perijinanApprovalDao;
	
	public PerijinanApprovalDao getPerijinanApprovalDao() {
		return perijinanApprovalDao;
	}
	
	@Autowired
	public void setPerijinanApprovalDao(PerijinanApprovalDao perijinanApprovalDao) {
		this.perijinanApprovalDao = perijinanApprovalDao;
	}

	public void save(PerijinanApproval perijinanApproval) {
		this.perijinanApprovalDao.save(perijinanApproval);
	}

	public List<PerijinanApproval> getPerijinanApprovals(List<Long> fileIds) {
		return this.perijinanApprovalDao.getPerijinanApprovals(fileIds);
	}

}
