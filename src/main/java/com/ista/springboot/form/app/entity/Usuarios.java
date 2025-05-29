package com.ista.springboot.form.app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuariosapi")
public class Usuarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuariosapi;

	private String cedula;
	@JsonProperty("primer_nombre")
	private String primerNombre;

	@JsonProperty("segundo_nombre")
	private String segundoNombre;

	@JsonProperty("primer_apellido")
	private String primerApellido;

	@JsonProperty("segundo_apellido")
	private String segundoApellido;
	private String correo;
	private String direccion;
	private String celular;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<UsuarioRol> roles = new HashSet<>();

	public Usuarios() {
		super();
	}

	public Usuarios(Long id_usuariosapi, String cedula, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String correo, String direccion, String celular,
			Set<UsuarioRol> roles) {
		super();
		this.id_usuariosapi = id_usuariosapi;
		this.cedula = cedula;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.correo = correo;
		this.direccion = direccion;
		this.celular = celular;
		this.roles = roles;
	}

	public Long getId_usuariosapi() {
		return id_usuariosapi;
	}

	public void setId_usuariosapi(Long id_usuariosapi) {
		this.id_usuariosapi = id_usuariosapi;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Set<UsuarioRol> getRoles() {
		return roles;
	}

	public void setRoles(Set<UsuarioRol> roles) {
		this.roles = roles;
	}

}
