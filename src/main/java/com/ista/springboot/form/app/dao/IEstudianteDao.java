package com.ista.springboot.form.app.dao;


import org.springframework.data.repository.CrudRepository;

import com.ista.springboot.form.app.entity.Estudiantes;


public interface IEstudianteDao extends CrudRepository <Estudiantes, Long>{

}
