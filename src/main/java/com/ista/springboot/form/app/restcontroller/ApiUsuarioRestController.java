package com.ista.springboot.form.app.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ista.springboot.form.app.entity.Usuarios;
import com.ista.springboot.form.app.services.IApiFenixService;

@RestController
@RequestMapping("/api/login")
public class ApiUsuarioRestController {

	 @Autowired
	    private IApiFenixService apiService;

	 
	 @GetMapping("/cedula/{cedula}")
	 public Usuarios buscarPorCedula(@PathVariable String cedula) {
		 try {
		        return apiService.buscarPorCedula(cedula);
		    } catch (RuntimeException e) {
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		    }
	 }
	 
	 @GetMapping("/buscarUsuario")
	    public List<Usuarios> buscar(@RequestParam String filtro) {
	        return apiService.buscarPorNombreOApellido(filtro);
	    }

	 

}
