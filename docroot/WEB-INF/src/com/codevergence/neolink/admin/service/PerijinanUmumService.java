package com.codevergence.neolink.admin.service;

import com.codevergence.neolink.admin.model.Branch;
import com.codevergence.neolink.admin.model.PerijinanUmum;

import java.util.List;

public interface PerijinanUmumService {
	public List<PerijinanUmum> getAll(boolean isActive, boolean isCompleteInformation, String name, String branchName);
	public PerijinanUmum getById(long id);
	public void insert(PerijinanUmum perijinanUmum);
	public void update(PerijinanUmum perijinanUmum);
	public void delete(long id);
	public List<Branch> getBranchHasDocument();
	public List<String> getNameHasDocument();
}
