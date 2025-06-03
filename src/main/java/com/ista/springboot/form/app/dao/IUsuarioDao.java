package com.ista.springboot.form.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ista.springboot.form.app.entity.Usuarios;

public interface IUsuarioDao extends CrudRepository <Usuarios, Long>{

	Optional<Usuarios> findByCedula(String cedula);
	
	List<Usuarios> findByPrimerNombreContainingIgnoreCaseOrPrimerApellidoContainingIgnoreCase(String nombre, String apellido);

}
