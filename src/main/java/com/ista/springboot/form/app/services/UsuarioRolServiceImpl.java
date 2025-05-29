package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.form.app.dao.IUsuarioRolDao;
import com.ista.springboot.form.app.entity.UsuarioRol;

@Service
public class UsuarioRolServiceImpl implements IUsuarioRolService{

	@Autowired
	private IUsuarioRolDao usuariorolDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<UsuarioRol> findAll() {
		// TODO Auto-generated method stub
		return (List<UsuarioRol>)usuariorolDao.findAll();
	}

	@Override
	@Transactional
	public UsuarioRol save(UsuarioRol usuariorol) {
		// TODO Auto-generated method stub
		return usuariorolDao.save(usuariorol);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioRol findById(Long id) {
		// TODO Auto-generated method stub
		return usuariorolDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		usuariorolDao.deleteById(id);
	}

}
