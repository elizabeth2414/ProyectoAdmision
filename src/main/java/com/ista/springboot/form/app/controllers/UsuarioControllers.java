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

import com.ista.springboot.form.app.entity.Usuarios;
import com.ista.springboot.form.app.services.IApiFenixService;
import com.ista.springboot.form.app.services.IRolService;

import jakarta.validation.Valid;

@Controller
public class UsuarioControllers {

	@Autowired
    private IApiFenixService usuarioService;

    @Autowired
    private IRolService rolService;

    @GetMapping("/listarUsuarios")
    public String listarUsuarios(Model model) {
        List<Usuarios> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Lista de Usuarios");
        return "listarUsuarios";
    }

    @GetMapping("/registrarUsuario")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new Usuarios());
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("titulo", "Registrar Usuario");
        return "registrarUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(
            @Valid @ModelAttribute("usuario") Usuarios usuario,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("titulo", "Registrar Usuario");
            return "registrarUsuario";
        }

        try {
            usuarioService.save(usuario);
            flash.addFlashAttribute("success", "Usuario guardado correctamente");
            return "redirect:/listarUsuarios";
        } catch (Exception e) {
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("error", "Error al guardar el usuario: " + e.getMessage());
            model.addAttribute("titulo", "Registrar Usuario");
            return "registrarUsuario";
        }
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Usuarios usuario = usuarioService.findById(id);
        if (usuario == null) {
            flash.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/listarUsuarios";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("titulo", "Editar Usuario");
        return "registrarUsuario";
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
