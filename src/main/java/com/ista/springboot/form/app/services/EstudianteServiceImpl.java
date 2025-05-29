package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.form.app.dao.IEstudianteDao;
import com.ista.springboot.form.app.entity.Estudiantes;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

	@Autowired
	private IEstudianteDao usuarioDao;


	@Override
	@Transactional(readOnly = true)
	public List<Estudiantes> findAll() {
		// TODO Auto-generated method stub
		return (List<Estudiantes>)usuarioDao.findAll();
	}

	@Override
	@Transactional
	public Estudiantes save(Estudiantes usuario) {
		// TODO Auto-generated method stub
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Estudiantes findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(id);
	}

}
