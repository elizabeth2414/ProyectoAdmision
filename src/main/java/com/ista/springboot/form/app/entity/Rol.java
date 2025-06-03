package com.ista.springboot.form.app.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Rol implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_roles;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private NombreRol nombre;

	private String claveSecreta;
	
	public enum NombreRol{
		SUPER_ADMIN,
		ADMINISTRADOR,
		DOCENTE
	}
	
	public Rol() {}
	
	
	public Rol(Long id_roles, NombreRol nombre) {
		super();
		this.id_roles = id_roles;
		this.nombre = nombre;
	}


	public Long getId_roles() {
		return id_roles;
	}

	public void setId_roles(Long id_roles) {
		this.id_roles = id_roles;
	}

	public NombreRol getNombre() {
		return nombre;
	}

	public void setNombre(NombreRol nombre) {
		this.nombre = nombre;
	}
	

	public String getClaveSecreta() {
		return claveSecreta;
	}


	public void setClaveSecreta(String claveSecreta) {
		this.claveSecreta = claveSecreta;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
