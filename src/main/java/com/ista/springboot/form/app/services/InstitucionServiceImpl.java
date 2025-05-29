package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.form.app.dao.IInstitucionDao;
import com.ista.springboot.form.app.entity.Institucion;

@Service
public class InstitucionServiceImpl implements IInstitucionService{

	@Autowired
	private IInstitucionDao institucionDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Institucion> findAll() {
		// TODO Auto-generated method stub
		return (List<Institucion>)institucionDao.findAll();
	}

	@Override
	@Transactional
	public Institucion save(Institucion institucion) {
		// TODO Auto-generated method stub
		return institucionDao.save(institucion);
	}

	@Override
	@Transactional(readOnly = true)
	public Institucion findById(Long id) {
		// TODO Auto-generated method stub
		return institucionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		institucionDao.deleteById(id);
	}

}
