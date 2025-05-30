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

import com.ista.springboot.form.app.entity.Carrera;
import com.ista.springboot.form.app.services.ICarreraServices;

@RestController
@RequestMapping("/api/carrera")
public class CarreraRestController {
	
	@Autowired
	private ICarreraServices carreraservices;
	
	
	@GetMapping("/list/estudiante")
	public List<Carrera> indext() {
		return carreraservices.findAll();
	}

	@PostMapping("/crear/estudiante")
	public Carrera save(@RequestBody Carrera carrera) {
		System.out.println(carrera);
		return carreraservices.save(carrera);
	}

	@GetMapping("/estudiante/{id}")
	public Carrera findById(@PathVariable Long id) {
		System.out.println(id);
		return carreraservices.findById(id);
	}

	@DeleteMapping("/eliminar/estudiante/{id}")
	public void deleteById(@PathVariable Long id) {
		System.out.println("id: " + id);
		carreraservices.delete(id);
	}
	
	@PutMapping("/actualizar/estudiante/{id}")
	public Carrera update(@RequestBody Carrera carreraActualizada, @PathVariable Long id ) {
		Carrera estudiante = carreraservices.findById(id);
		return carreraservices.save(estudiante);
	}
	
	 @GetMapping("/todos")
	    public List<Carrera> listarTodos() {
	        return carreraservices.findAll();
	    }
	

}
