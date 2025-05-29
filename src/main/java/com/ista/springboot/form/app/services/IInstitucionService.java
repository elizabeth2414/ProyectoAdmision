package com.ista.springboot.form.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.springboot.form.app.entity.Institucion;


@Service
public interface IInstitucionService {

	List<Institucion> findAll();

	Institucion save(Institucion institucion);

	Institucion findById(Long id);

	void delete(Long id);
}
