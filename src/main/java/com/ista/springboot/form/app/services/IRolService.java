package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.springboot.form.app.entity.Rol;

@Service
public interface IRolService {

	List<Rol> findAll();
	
	Rol buscarPorNombre(String nombre);

	Rol save(Rol roles);

	Rol findById(Long id);

	void delete(Long id);
}
