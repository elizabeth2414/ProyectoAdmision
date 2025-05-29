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

import com.ista.springboot.form.app.entity.Estudiantes;
import com.ista.springboot.form.app.services.IEstudianteService;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteRestController {

	@Autowired
	private IEstudianteService estudianteService;
	

	@GetMapping("/list/estudiante")
	public List<Estudiantes> indext() {
		return estudianteService.findAll();
	}

	@PostMapping("/crear/estudiante")
	public Estudiantes save(@RequestBody Estudiantes estudiante) {
		System.out.println(estudiante);
		return estudianteService.save(estudiante);
	}

	@GetMapping("/estudiante/{id}")
	public Estudiantes findById(@PathVariable Long id) {
		System.out.println(id);
		return estudianteService.findById(id);
	}

	@DeleteMapping("/eliminar/estudiante/{id}")
	public void deleteById(@PathVariable Long id) {
		System.out.println("id: " + id);
		estudianteService.delete(id);
	}
	
	@PutMapping("/actualizar/estudiante/{id}")
	public Estudiantes update(@RequestBody Estudiantes estudianteActualizada, @PathVariable Long id ) {
		Estudiantes estudiante = estudianteService.findById(id);
		return estudianteService.save(estudiante);
	}
	
	 @GetMapping("/todos")
	    public List<Estudiantes> listarTodos() {
	        return estudianteService.findAll();
	    }
	
}
