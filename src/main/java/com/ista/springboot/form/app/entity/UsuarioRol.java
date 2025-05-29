package com.ista.springboot.form.app.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarioroles")
public class UsuarioRol implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuariorol;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuarios usuario;

	@ManyToOne
	@JoinColumn(name = "rol_id")
	private Rol rol;

	public UsuarioRol() {
	}

	public Long getId_usuariorol() {
		return id_usuariorol;
	}

	public void setId_usuariorol(Long id_usuariorol) {
		this.id_usuariorol = id_usuariorol;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
