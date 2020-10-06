package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.Branch;
import com.codevergence.neolink.admin.model.PerijinanBI;

import java.util.Date;
import java.util.List;

public interface PerijinanBIService {
	public List<PerijinanBI> getAll(boolean isActive, boolean isCompleteInformation, String name, String branchName);
	public PerijinanBI getById(long id);
	public void insert(PerijinanBI perijinanBI);
	public void update(PerijinanBI perijinanBI);
	public void delete(long id);
	public List<Branch> getBranchHasDocument();
	public List<String> getNameHasDocument();
	
	public void toggleRecap(boolean isRecap, long id);
	public void toggleRecapEfective(boolean isRecapEfective, long id);
	
	public void updateOpeningDateMasterBranch(Date openingDate, long dokumenBiId);
}
