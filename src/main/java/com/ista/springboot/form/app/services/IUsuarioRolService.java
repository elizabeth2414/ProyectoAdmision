package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.springboot.form.app.entity.UsuarioRol;

@Service
public interface IUsuarioRolService {

	List<UsuarioRol> findAll();

	UsuarioRol save(UsuarioRol usuariorol);

	UsuarioRol findById(Long id);

	void delete(Long id);
}
