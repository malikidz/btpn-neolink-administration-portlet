package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.InvestStandardBIDao;
import com.codevergence.neolink.admin.model.InvestStandardBI;
import com.codevergence.neolink.admin.service.InvestStandardBIService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("investStandardBIService")
public class InvestStandardBIServiceImpl implements InvestStandardBIService{

	@Qualifier("investStandardBIDao")
	private InvestStandardBIDao investStandardBIDao ;
	
	public InvestStandardBIDao getInvestStandardBIDao() {
		return investStandardBIDao;
	}

	@Autowired
	public void setInvestStandardBIDao(InvestStandardBIDao investStandardBIDao) {
		this.investStandardBIDao = investStandardBIDao;
	}

	public List<InvestStandardBI> getAll() {
		return investStandardBIDao.getAll();
	}


	public void insert(InvestStandardBI investStandardBI) {
		investStandardBIDao.insert(investStandardBI);
		
	}

	public void update(InvestStandardBI investStandardBI) {
		investStandardBIDao.update(investStandardBI);
		
	}

	public void delete(long id) {
		investStandardBIDao.delete(id);
		
	}

	public InvestStandardBI withId(long id) {
		return investStandardBIDao.withId(id);
	}

}
