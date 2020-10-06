package com.codevergence.neolink.admin.service.impl;

import com.codevergence.neolink.admin.dao.ZonaBIDao;
import com.codevergence.neolink.admin.model.ZonaBI;
import com.codevergence.neolink.admin.service.ZonaBIService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("zonaBiService")
public class ZonaBIServiceImple implements ZonaBIService {

	@Qualifier("zonaBiDao")
	private ZonaBIDao zonaBiDao;

	public ZonaBIDao getZonaBIDao() {
		return zonaBiDao;
	}

	@Autowired
	public void setZonaBIDao(ZonaBIDao zonaBiDao) {
		this.zonaBiDao = zonaBiDao;
	}

	public List<ZonaBI> getAll() {
		return zonaBiDao.getAll();
	}

	public void insert(ZonaBI zonabi) {
		zonaBiDao.insert(zonabi);
	}

	public void update(ZonaBI zonabi) {
		zonaBiDao.update(zonabi);
	}

	public ZonaBI getById(long id) {
		return zonaBiDao.getById(id);
	}

	public void delete(long id) {
		zonaBiDao.delete(id);
	}

}
