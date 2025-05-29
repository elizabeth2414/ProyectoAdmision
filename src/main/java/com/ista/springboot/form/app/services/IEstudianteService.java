package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.springboot.form.app.entity.Estudiantes;


@Service
public interface IEstudianteService {

	List<Estudiantes> findAll();
	
	Estudiantes save(Estudiantes estudiantes);

	Estudiantes findById(Long id);

	void delete(Long id);
}
