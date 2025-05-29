package com.ista.springboot.form.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ista.springboot.form.app.entity.Institucion;
import com.ista.springboot.form.app.entity.Rol;
import com.ista.springboot.form.app.entity.Estudiantes;
import com.ista.springboot.form.app.services.IInstitucionService;
import com.ista.springboot.form.app.services.IRolService;
import com.ista.springboot.form.app.services.IEstudianteService;

import jakarta.validation.Valid;

@Controller
public class EstudianteControllers {

	@Autowired
    private IEstudianteService usuarioService;

    @Autowired
    private IInstitucionService institucionService;

    @Autowired
    private IRolService rolService;
    
    @GetMapping("/listartodos")
    public String listarTodos(Model model) {
        List<Estudiantes> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "listartodos";
    }

    @GetMapping("/listarUsuarios")
    public String listarUsuarios(Model model) {
        List<Estudiantes> usuarios = usuarioService.findAll();
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("usuarios", usuarios);
        return "listarUsuarios";
    }

    @GetMapping("/registrarEstudiantes")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new Estudiantes());

        List<Institucion> instituciones = institucionService.findAll();
        model.addAttribute("instituciones", instituciones);

        List<Rol> roles = rolService.findAll(); 
        model.addAttribute("roles", roles);

        model.addAttribute("titulo", "Registrar Usuario");
        return "registrarEstudiantes";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(
            @Valid @ModelAttribute("usuario") Estudiantes usuario,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registrar Usuario");

           
            model.addAttribute("instituciones", institucionService.findAll());
            model.addAttribute("roles", rolService.findAll());

            return "registrarEstudiantes";
        }

        try {
            usuarioService.save(usuario);
            flash.addFlashAttribute("success", "Usuario guardado correctamente");
            return "redirect:/listarUsuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el usuario: " + e.getMessage());
            model.addAttribute("titulo", "Registrar Usuario");

            model.addAttribute("instituciones", institucionService.findAll());
            model.addAttribute("roles", rolService.findAll());

            return "registrarEstudiantes";
        }
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Estudiantes usuario = usuarioService.findById(id);
        if (usuario == null) {
            flash.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/listarUsuarios";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Editar Usuario");
        model.addAttribute("instituciones", institucionService.findAll());
        model.addAttribute("roles", rolService.findAll());
        return "registrarEstudiantes";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes flash) {
        try {
            usuarioService.delete(id);
            flash.addFlashAttribute("success", "Usuario eliminado correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
        }
        return "redirect:/listarUsuarios";
    }
}
