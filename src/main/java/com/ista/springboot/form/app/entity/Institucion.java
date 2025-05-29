package com.ista.springboot.form.app.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="instituciones")
public class Institucion implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_institucion;
	
	private String amie;
	private String nombre_institucion;
	private String Zona;
	private String provincia;
	private String canton;
	private String parroquia;
	private String cod_distrital;
	private int total_estudiantes;
	private Date fecha;
	private String representante_legal;
	private String numero_contacto;
	private String correo;
	
	@OneToMany(mappedBy = "institucion")
	private List<Estudiantes> usuarios = new ArrayList<>();
	
	public Institucion() {}
	
	
	public Institucion(Long id_institucion, String amie, String nombre_institucion, String zona, String provincia,
			String canton, String parroquia, String cod_distrital, int total_estudiantes, Date fecha,
			String representante_legal, String numero_contacto, String correo) {
		super();
		this.id_institucion = id_institucion;
		this.amie = amie;
		this.nombre_institucion = nombre_institucion;
		Zona = zona;
		this.provincia = provincia;
		this.canton = canton;
		this.parroquia = parroquia;
		this.cod_distrital = cod_distrital;
		this.total_estudiantes = total_estudiantes;
		this.fecha = fecha;
		this.representante_legal = representante_legal;
		this.numero_contacto = numero_contacto;
		this.correo = correo;
	}


	
	public Long getId_institucion() {
		return id_institucion;
	}


	public void setId_institucion(Long id_institucion) {
		this.id_institucion = id_institucion;
	}


	public String getAmie() {
		return amie;
	}


	public void setAmie(String amie) {
		this.amie = amie;
	}


	public String getNombre_institucion() {
		return nombre_institucion;
	}


	public void setNombre_institucion(String nombre_institucion) {
		this.nombre_institucion = nombre_institucion;
	}


	public String getZona() {
		return Zona;
	}


	public void setZona(String zona) {
		Zona = zona;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getCanton() {
		return canton;
	}


	public void setCanton(String canton) {
		this.canton = canton;
	}


	public String getParroquia() {
		return parroquia;
	}


	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}


	public String getCod_distrital() {
		return cod_distrital;
	}


	public void setCod_distrital(String cod_distrital) {
		this.cod_distrital = cod_distrital;
	}


	public int getTotal_estudiantes() {
		return total_estudiantes;
	}


	public void setTotal_estudiantes(int total_estudiantes) {
		this.total_estudiantes = total_estudiantes;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getRepresentante_legal() {
		return representante_legal;
	}


	public void setRepresentante_legal(String representante_legal) {
		this.representante_legal = representante_legal;
	}


	public String getNumero_contacto() {
		return numero_contacto;
	}


	public void setNumero_contacto(String numero_contacto) {
		this.numero_contacto = numero_contacto;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
