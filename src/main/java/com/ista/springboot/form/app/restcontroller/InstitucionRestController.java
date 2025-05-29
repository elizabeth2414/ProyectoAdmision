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

import com.ista.springboot.form.app.entity.Institucion;
import com.ista.springboot.form.app.services.IInstitucionService;

@RestController
@RequestMapping("/api/institucion")
public class InstitucionRestController {

	@Autowired
	private IInstitucionService institucionService;
	
	@GetMapping("/list/institucion")
	public List<Institucion> indext() {
		return institucionService.findAll();
	}

	@PostMapping("/crear/institucion")
	public Institucion save(@RequestBody Institucion institucion) {
		System.out.println(institucion);
		return institucionService.save(institucion);
	}

	@GetMapping("/institucion/{id}")
	public Institucion findById(@PathVariable Long id) {
		System.out.println(id);
		return institucionService.findById(id);
	}

	@DeleteMapping("/eliminar/institucion/{id}")
	public void deleteById(@PathVariable Long id) {
		System.out.println("id: " + id);
		institucionService.delete(id);
	}
	
	@PutMapping("/actualizar/institucion/{id}")
	public Institucion update(@RequestBody Institucion institucionActualizada, @PathVariable Long id ) {
		Institucion institucion = institucionService.findById(id);
		return institucionService.save(institucion);
	}
}
