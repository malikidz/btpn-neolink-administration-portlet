package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.LineOfBusinessDao;
import com.codevergence.neolink.admin.model.LineOfBusiness;
import com.codevergence.neolink.admin.service.LineOfBusinessService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("lineOfBusinessService")
public class LineOfBusinessServiceImpl implements LineOfBusinessService {
	
	@Qualifier("lineOfBusinessDao")
	private LineOfBusinessDao lineOfBusinessDao;

	public LineOfBusinessDao getLineOfBusinessDao() {
		return lineOfBusinessDao;
	}

	@Autowired
	public void setParameterDao(LineOfBusinessDao lineOfBusinessDao) {
		this.lineOfBusinessDao = lineOfBusinessDao;
	}
	
	public List<LineOfBusiness> getAll() {
		return this.lineOfBusinessDao.getAll();
	}

	public LineOfBusiness getById(long id) {
		return this.lineOfBusinessDao.getById(id);
	}

	public void insert(LineOfBusiness lob) {
		this.lineOfBusinessDao.insert(lob);
	}

	public void update(LineOfBusiness lob) {
		this.lineOfBusinessDao.update(lob);
	}

	public void delete(long id) {
		this.lineOfBusinessDao.delete(id);
	}

	public LineOfBusiness getByUnitBusinessId(String unitBusinessId) {
		return this.lineOfBusinessDao.getByUnitBusinessId(unitBusinessId);
	}

}
