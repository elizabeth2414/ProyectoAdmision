package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.springboot.form.app.entity.Carrera;


@Service
public interface ICarreraServices {

	List<Carrera> findAll();

	Carrera save(Carrera carrera);

	Carrera findById(Long id);

	void delete(Long id);
}
