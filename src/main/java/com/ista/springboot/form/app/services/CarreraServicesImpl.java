package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.form.app.dao.ICarreraDao;
import com.ista.springboot.form.app.entity.Carrera;

@Service
public class CarreraServicesImpl implements ICarreraServices{
	
	@Autowired
	private ICarreraDao carreradao;

	@Override
	@Transactional(readOnly = true)
	public List<Carrera> findAll() {
		return (List<Carrera>)carreradao.findAll();
	}

	@Override
	@Transactional
	public Carrera save(Carrera carrera) {
		return carreradao.save(carrera);
	}

	@Override
	@Transactional(readOnly = true)
	public Carrera findById(Long id) {
		return carreradao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		carreradao.deleteById(id);
	}

}
