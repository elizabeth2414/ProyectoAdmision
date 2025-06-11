package com.ista.springboot.form.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ista.springboot.form.app.entity.Rol;
import com.ista.springboot.form.app.entity.Rol.NombreRol;
import com.ista.springboot.form.app.entity.UsuarioRol;
import com.ista.springboot.form.app.entity.Usuarios;
import com.ista.springboot.form.app.services.IApiFenixService;
import com.ista.springboot.form.app.services.IRolService;

@Controller
public class AsignarRolControllers {
	
	@Autowired
	private IApiFenixService usuarioService; // o UsuarioRepository

	@Autowired
	private IRolService rolService; // o RolRepository
	
	 @GetMapping("/buscar")
	    @ResponseBody
	    public ResponseEntity<List<Usuarios>> buscarUsuarios(@RequestParam("query") String query) {
	        List<Usuarios> usuarios = usuarioService.buscarUsuariosSinRol(query);
	        return ResponseEntity.ok(usuarios);
	    }

	    // Ruta para asignar rol a un usuario
	    @PostMapping("/asignar-rol")
	    public String asignarRolAUsuario(@RequestParam("usuarioId") Long usuarioId, RedirectAttributes flash) {
	        // Buscar el usuario
	        Usuarios usuario = usuarioService.findById(usuarioId);
	        if (usuario == null) {
	            flash.addFlashAttribute("error", "Usuario no encontrado.");
	            return "redirect:/listarRolUsuario";
	        }

	        // Buscar el rol ADMINISTRADOR
	        Rol rol = rolService.buscarPorNombre(Rol.NombreRol.ADMINISTRADOR);
	        if (rol == null) {
	            flash.addFlashAttribute("error", "Rol ADMINISTRADOR no encontrado.");
	            return "redirect:/listarRolUsuario";
	        }

	        // Crear relación intermedia
	        UsuarioRol usuarioRol = new UsuarioRol();
	        usuarioRol.setUsuario(usuario);
	        usuarioRol.setRol(rol);

	        // Agregar a la lista del usuario
	        usuario.getRoles().add(usuarioRol);

	        // Guardar usuario
	        usuarioService.save(usuario);

	        flash.addFlashAttribute("success", "Rol asignado correctamente.");
	        return "redirect:/listarRolUsuario";
	    }

	    // Nueva ruta para obtener la lista de administradores
	    @GetMapping("/api/rolusuario/administradores")
	    @ResponseBody
	    public ResponseEntity<List<Usuarios>> obtenerAdministradores() {
	        List<Usuarios> administradores = usuarioService.obtenerAdministradores();
	        return ResponseEntity.ok(administradores);
	    }
	
}
