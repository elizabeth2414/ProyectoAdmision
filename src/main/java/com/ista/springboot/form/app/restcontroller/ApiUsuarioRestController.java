package com.ista.springboot.form.app.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ista.springboot.form.app.entity.Usuarios;
import com.ista.springboot.form.app.services.IApiFenixService;

@RestController
@RequestMapping("/api/login")
public class ApiUsuarioRestController {

	@Autowired
	private IApiFenixService apiService;

	@GetMapping("/cedula/{cedula}")
	public ResponseEntity<?> buscarPorCedula(@PathVariable String cedula) {
		try {
			Usuarios usuario = apiService.obtenerORegistrarUsuarioPorCedula(cedula); // ✔️
			return ResponseEntity.ok(usuario);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("error", "Usuario no encontrado con la cédula " + cedula));
		}
	}

	@GetMapping("/buscarUsuario")
	public List<Usuarios> buscar(@RequestParam String filtro) {
		return apiService.buscarPorNombreOApellido(filtro);
	}

}
