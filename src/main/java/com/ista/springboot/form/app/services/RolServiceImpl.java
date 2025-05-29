package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.form.app.dao.IRolDao;
import com.ista.springboot.form.app.entity.Rol;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	private IRolDao rolDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		return (List<Rol>)rolDao.findAll();
	}

	@Override
	@Transactional
	public Rol save(Rol roles) {
		// TODO Auto-generated method stub
		return rolDao.save(roles);
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findById(Long id) {
		// TODO Auto-generated method stub
		return rolDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		rolDao.deleteById(id);
	}

	@Override
	public Rol buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return rolDao.findByNombre(nombre);
	}

	
}
