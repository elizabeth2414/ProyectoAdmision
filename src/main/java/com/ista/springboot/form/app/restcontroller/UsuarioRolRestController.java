package com.ista.springboot.form.app.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.springboot.form.app.entity.Rol;
import com.ista.springboot.form.app.entity.UsuarioRol;
import com.ista.springboot.form.app.entity.Usuarios;
import com.ista.springboot.form.app.services.IApiFenixService;
import com.ista.springboot.form.app.services.IRolService;
import com.ista.springboot.form.app.services.IUsuarioRolService;

@RestController
@RequestMapping("/api/usuariorol")
public class UsuarioRolRestController {

	@Autowired
	private IUsuarioRolService usuariorolService;
	
	@Autowired
	private IApiFenixService usuarioService;
	
	@Autowired
	private IRolService rolService;
	
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
	@PostMapping("/asignar")
	public ResponseEntity<String> asignarRol(@RequestBody Map<String, Object> datos) {
	    System.out.println("Datos recibidos: " + datos);

	    // Validar que los datos contengan las claves necesarias
	    if (!datos.containsKey("usuarioId") || !datos.containsKey("rol")) {
	        return ResponseEntity.badRequest().body("Faltan datos obligatorios: usuarioId o rol");
	    }

	    try {
	        String usuarioIdStr = String.valueOf(datos.get("usuarioId"));

	        // Validar que el usuarioId no sea undefined o vacío
	        if (usuarioIdStr == null || usuarioIdStr.isEmpty() || usuarioIdStr.equals("undefined")) {
	            return ResponseEntity.badRequest().body("ID de usuario inválido");
	        }

	        Long usuarioId = Long.parseLong(usuarioIdStr);
	        String nombreRolStr = datos.get("rol").toString().toUpperCase();

	        System.out.println("usuarioId: " + usuarioId + ", rol: " + nombreRolStr);

	        // Buscar usuario
	        Usuarios usuario = usuarioService.buscarPorId(usuarioId);
	        if (usuario == null) {
	            return ResponseEntity.badRequest().body("Usuario no encontrado");
	        }

	        // Validar nombre de rol
	        Rol.NombreRol nombreRol;
	        try {
	            nombreRol = Rol.NombreRol.valueOf(nombreRolStr);
	        } catch (IllegalArgumentException ex) {
	            return ResponseEntity.badRequest().body("Rol inválido: " + nombreRolStr);
	        }

	        // Buscar rol
	        Rol rol = rolService.buscarPorNombre(nombreRol);
	        if (rol == null) {
	            return ResponseEntity.badRequest().body("Rol no encontrado");
	        }

	        // Crear relación y guardar
	        UsuarioRol usuarioRol = new UsuarioRol();
	        usuarioRol.setUsuario(usuario);
	        usuarioRol.setRol(rol);
	        usuariorolService.save(usuarioRol);

	        return ResponseEntity.ok("Rol asignado correctamente");

	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body("ID de usuario inválido: debe ser un número");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body("Error al asignar el rol: " + e.getMessage());
	    }
	}


	
	@GetMapping("")
	public List<UsuarioRol> listarTodos() {
	    return usuariorolService.findAll();
	}

}
