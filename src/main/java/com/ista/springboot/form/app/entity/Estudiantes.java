package com.ista.springboot.form.app.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="estudiantes")
public class Estudiantes implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_usuario;
	
	private String cedula;
	private String nombre;
	private String apellido;
	private String correo;
	private String celular;
	private boolean matriculado;
	private boolean apoyaproceso;
	private String observacion;
	
	@ManyToOne
	@JoinColumn(name = "institucion")
	private Institucion institucion;
	
	public Estudiantes() {}


	public Estudiantes(Long id_usuario, String cedula, String nombre, String apellido, String correo, String celular,
			boolean matriculado, boolean apoyaproceso, String observacion, Institucion institucion) {
		super();
		this.id_usuario = id_usuario;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.celular = celular;
		this.matriculado = matriculado;
		this.apoyaproceso = apoyaproceso;
		this.observacion = observacion;
		this.institucion = institucion;
	}




	public Long getId_usuario() {
		return id_usuario;
	}




	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}




	public String getCedula() {
		return cedula;
	}




	public void setCedula(String cedula) {
		this.cedula = cedula;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getApellido() {
		return apellido;
	}




	public void setApellido(String apellido) {
		this.apellido = apellido;
	}




	public String getCorreo() {
		return correo;
	}




	public void setCorreo(String correo) {
		this.correo = correo;
	}




	public String getCelular() {
		return celular;
	}




	public void setCelular(String celular) {
		this.celular = celular;
	}




	public boolean isMatriculado() {
		return matriculado;
	}




	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}




	public boolean isApoyaproceso() {
		return apoyaproceso;
	}




	public void setApoyaproceso(boolean apoyaproceso) {
		this.apoyaproceso = apoyaproceso;
	}




	public String getObservacion() {
		return observacion;
	}




	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}




	public Institucion getInstitucion() {
		return institucion;
	}




	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
