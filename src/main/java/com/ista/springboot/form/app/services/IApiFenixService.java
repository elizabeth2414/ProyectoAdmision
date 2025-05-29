package com.ista.springboot.form.app.services;

import java.util.List;

import com.ista.springboot.form.app.entity.Usuarios;

public interface IApiFenixService {

	Usuarios buscarPorCedula(String cedula);
	
	List<Usuarios> buscarPorNombreOApellido(String filtro);
	
	Usuarios buscarPorId(Long id);

	Usuarios obtenerORegistrarUsuarioPorCedula(String cedula);
	
	List<Usuarios> findAll();

	Usuarios save(Usuarios usuarios);

	Usuarios findById(Long id);

	void delete(Long id);
}
