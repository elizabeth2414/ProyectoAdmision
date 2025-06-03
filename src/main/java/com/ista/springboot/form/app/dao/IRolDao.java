package com.ista.springboot.form.app.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ista.springboot.form.app.entity.Rol;
import com.ista.springboot.form.app.entity.Rol.NombreRol;

public interface IRolDao extends CrudRepository <Rol, Long>{

	Optional<Rol> findByNombre(NombreRol nombre);
	Optional<Rol> findByNombreAndClaveSecreta(NombreRol nombre, String claveSecreta);

}