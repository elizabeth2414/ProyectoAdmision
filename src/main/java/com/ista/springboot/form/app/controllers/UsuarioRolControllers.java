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

import com.ista.springboot.form.app.entity.Rol;
import com.ista.springboot.form.app.entity.Estudiantes;
import com.ista.springboot.form.app.entity.UsuarioRol;
import com.ista.springboot.form.app.services.IRolService;
import com.ista.springboot.form.app.services.IUsuarioRolService;
import com.ista.springboot.form.app.services.IEstudianteService;

import jakarta.validation.Valid;

@Controller
public class UsuarioRolControllers {

	@Autowired
    private IUsuarioRolService usuarioRolService;

    @Autowired
    private IEstudianteService usuarioService;

    @Autowired
    private IRolService rolService;

    @GetMapping("/listarRolUsuario")
    public String listarUsuarioRoles(Model model) {
        List<UsuarioRol> usuarioRoles = usuarioRolService.findAll();
        model.addAttribute("titulo", "Lista de Roles por Usuario");
        model.addAttribute("usuarioRoles", usuarioRoles);
        return "listarRolUsuario";
    }

    @GetMapping("/usuarioRol")
    public String crearUsuarioRol(Model model) {
        model.addAttribute("usuarioRol", new UsuarioRol());

        List<Estudiantes> usuarios = usuarioService.findAll();
        List<Rol> roles = rolService.findAll();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);

        model.addAttribute("titulo", "Asignar Rol a Usuario");
        return "usuarioRol";
    }

    @PostMapping("/guardarUsuarioRol")
    public String guardarUsuarioRol(
            @Valid @ModelAttribute("usuarioRol") UsuarioRol usuarioRol,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Asignar Rol a Usuario");
            model.addAttribute("usuarios", usuarioService.findAll());
            model.addAttribute("roles", rolService.findAll());
            return "usuarioRol";
        }

        try {
            usuarioRolService.save(usuarioRol);
            flash.addFlashAttribute("success", "Rol asignado correctamente");
            return "redirect:/listarRolUsuario";
        } catch (Exception e) {
            model.addAttribute("error", "Error al asignar el rol: " + e.getMessage());
            model.addAttribute("titulo", "Asignar Rol a Usuario");
            model.addAttribute("usuarios", usuarioService.findAll());
            model.addAttribute("roles", rolService.findAll());
            return "usuarioRol";
        }
    }

    @GetMapping("/editarUsuarioRol/{id}")
    public String editarUsuarioRol(@PathVariable Long id, Model model, RedirectAttributes flash) {
        UsuarioRol usuarioRol = usuarioRolService.findById(id);
        if (usuarioRol == null) {
            flash.addFlashAttribute("error", "Asignación de rol no encontrada");
            return "redirect:/listarRolUsuario";
        }
        model.addAttribute("usuarioRol", usuarioRol);
        model.addAttribute("titulo", "Editar Asignación de Rol");
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("roles", rolService.findAll());
        return "usuarioRol";
    }

    @GetMapping("/eliminarUsuarioRol/{id}")
    public String eliminarUsuarioRol(@PathVariable Long id, RedirectAttributes flash) {
        try {
            usuarioRolService.delete(id);
            flash.addFlashAttribute("success", "Asignación de rol eliminada correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al eliminar la asignación: " + e.getMessage());
        }
        return "redirect:/listarRolUsuario";
    }
}
