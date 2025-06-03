package com.ista.springboot.form.app.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ista.springboot.form.app.entity.Rol;
import com.ista.springboot.form.app.entity.Rol.NombreRol;
import com.ista.springboot.form.app.entity.Usuarios;
import com.ista.springboot.form.app.entity.UsuarioRol;
import com.ista.springboot.form.app.services.IRolService;
import com.ista.springboot.form.app.services.IUsuarioRolService;
import com.ista.springboot.form.app.services.IApiFenixService;

@RestController
@RequestMapping("/api/rol")
public class RolRestController {

	@Autowired
	private IRolService rolService;
	
	@Autowired
	private IApiFenixService usuarioService;
	
	@Autowired
	private IUsuarioRolService rolUsuarioService;
	
	@GetMapping("/validar-clave")
	public ResponseEntity<?> validarClaveSecreta(@RequestParam String rol, @RequestParam String clave) {
	    try {
	        Rol.NombreRol nombreRol = Rol.NombreRol.valueOf(rol.toUpperCase());
	        Rol rolEncontrado = rolService.buscarPorNombreYClave(nombreRol, clave);

	        if (rolEncontrado != null) {
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clave secreta incorrecta");
	        }
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body("Rol no válido");
	    }
	}

	
	@GetMapping("/list/rol")
	public List<Rol> indext() {
		return rolService.findAll();
	}

	@PostMapping("/crear/rol")
	public Rol save(@RequestBody Rol roles) {
		System.out.println(roles);
		return rolService.save(roles);
	}

	@GetMapping("/rol/{id}")
	public Rol findById(@PathVariable Long id) {
		System.out.println(id);
		return rolService.findById(id);
	}

	@DeleteMapping("/eliminar/rol/{id}")
	public void deleteById(@PathVariable Long id) {
		System.out.println("id: " + id);
		rolService.delete(id);
	}
	
	@PutMapping("/actualizar/rol/{id}")
	public Rol update(@RequestBody Rol rolesActualizada, @PathVariable Long id ) {
		Rol roles = rolService.findById(id);
		return rolService.save(roles);
	}
	
	@PostMapping("/asignar")
	public ResponseEntity<String> asignarRol(@RequestBody Map<String, Object> datos) {
	    Long usuarioId = Long.valueOf(datos.get("usuarioId").toString());
	    String nombreRolStr = datos.get("rol").toString();

	    Usuarios usuario = usuarioService.buscarPorId(usuarioId);

	    Rol.NombreRol nombreRol;
	    try {
	        nombreRol = Rol.NombreRol.valueOf(nombreRolStr.toUpperCase());
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body("Rol no válido");
	    }

	    Rol rol = rolService.buscarPorNombre(nombreRol);
	    
	    if (usuario == null || rol == null) {
	        return ResponseEntity.badRequest().body("Usuario o Rol no encontrado");
	    }

	    UsuarioRol ru = new UsuarioRol();
	    ru.setUsuario(usuario);
	    ru.setRol(rol);
	    rolUsuarioService.save(ru);

	    return ResponseEntity.ok("Rol asignado correctamente");
	}

}
