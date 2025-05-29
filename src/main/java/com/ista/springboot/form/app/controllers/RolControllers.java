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
import com.ista.springboot.form.app.entity.Rol.NombreRol;
import com.ista.springboot.form.app.services.IRolService;

import jakarta.validation.Valid;

@Controller
public class RolControllers {

	@Autowired
    private IRolService rolService;
	
	@GetMapping("/asignarrol")
    public String mostrarFormularioRol() {
        return "asignarrol";
    }

    @GetMapping("/listarRoles")
    public String listarRoles(Model model) {
        List<Rol> roles = rolService.findAll();
        model.addAttribute("titulo", "Lista de Roles");
        model.addAttribute("roles", roles);
        return "listarRoles";
    }

    @GetMapping("/rol")
    public String crearRol(Model model) {
        model.addAttribute("rol", new Rol());
        model.addAttribute("nombresRoles", NombreRol.values());
        model.addAttribute("titulo", "Registrar Rol");
        return "rol";
    }

    @PostMapping("/guardarRol")
    public String guardarRol(
            @Valid @ModelAttribute("rol") Rol rol,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registrar Rol");
            model.addAttribute("nombresRoles", NombreRol.values());
            return "rol";
        }

        try {
            rolService.save(rol);
            flash.addFlashAttribute("success", "Rol guardado correctamente");
            return "redirect:/listarRoles";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el rol: " + e.getMessage());
            model.addAttribute("titulo", "Registrar Rol");
            model.addAttribute("nombresRoles", NombreRol.values());
            return "rol";
        }
    }

    @GetMapping("/editarRol/{id}")
    public String editarRol(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Rol rol = rolService.findById(id);
        if (rol == null) {
            flash.addFlashAttribute("error", "Rol no encontrado");
            return "redirect:/listarRoles";
        }
        model.addAttribute("rol", rol);
        model.addAttribute("nombresRoles", NombreRol.values());
        model.addAttribute("titulo", "Editar Rol");
        return "rol";
    }

    @GetMapping("/eliminarRol/{id}")
    public String eliminarRol(@PathVariable Long id, RedirectAttributes flash) {
        try {
            rolService.delete(id);
            flash.addFlashAttribute("success", "Rol eliminado correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al eliminar el rol: " + e.getMessage());
        }
        return "redirect:/listarRoles";
    }
}
