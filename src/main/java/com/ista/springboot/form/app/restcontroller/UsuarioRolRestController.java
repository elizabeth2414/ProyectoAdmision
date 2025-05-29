package com.ista.springboot.form.app.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.springboot.form.app.entity.UsuarioRol;
import com.ista.springboot.form.app.services.IUsuarioRolService;

@RestController
@RequestMapping("/api/usuariorol")
public class UsuarioRolRestController {

	@Autowired
	private IUsuarioRolService usuariorolService;
	
	@GetMapping("/list/usuariorol")
	public List<UsuarioRol> indext() {
		return usuariorolService.findAll();
	}

	@PostMapping("/crear/usuariorol")
	public UsuarioRol save(@RequestBody UsuarioRol usuariorol) {
		System.out.println(usuariorol);
		return usuariorolService.save(usuariorol);
	}

	@GetMapping("/usuariorol/{id}")
	public UsuarioRol findById(@PathVariable Long id) {
		System.out.println(id);
		return usuariorolService.findById(id);
	}

	@DeleteMapping("/eliminar/usuariorol/{id}")
	public void deleteById(@PathVariable Long id) {
		System.out.println("id: " + id);
		usuariorolService.delete(id);
	}
	
	@PutMapping("/actualizar/usuariorol/{id}")
	public UsuarioRol update(@RequestBody UsuarioRol usuariorolActualizada, @PathVariable Long id ) {
		UsuarioRol usuariorol = usuariorolService.findById(id);
		return usuariorolService.save(usuariorol);
	}
}
