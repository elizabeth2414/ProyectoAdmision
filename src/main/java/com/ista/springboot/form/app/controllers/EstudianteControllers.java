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

import com.ista.springboot.form.app.entity.Estudiantes;
import com.ista.springboot.form.app.services.IInstitucionService;
import com.ista.springboot.form.app.services.IEstudianteService;

import jakarta.validation.Valid;

@Controller
public class EstudianteControllers {

	@Autowired
    private IEstudianteService estudianteService;

    @Autowired
    private IInstitucionService institucionService;

    @GetMapping("/listarEstudiantes")
    public String listarUsuarios(Model model) {
        List<Estudiantes> estudiantes = estudianteService.findAll();
        model.addAttribute("titulo", "Lista de Estudiantes");
        model.addAttribute("estudiantes", estudiantes);
        return "listarEstudiantes";
    }

    @GetMapping("/registrarEstudiantes")
    public String crearUsuario(Model model) {
        model.addAttribute("estudiante", new Estudiantes());
        model.addAttribute("instituciones", institucionService.findAll());
        model.addAttribute("titulo", "Registrar Usuario");
        return "registrarEstudiantes";
    }

    @PostMapping("/guardarEstudiante")
    public String guardarUsuario(
            @Valid @ModelAttribute("estudiante") Estudiantes estudiante,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registrar Usuario");
            model.addAttribute("instituciones", institucionService.findAll());
            return "registrarEstudiantes";
        }

        try {
        	estudianteService.save(estudiante);
            flash.addFlashAttribute("success", "Estudiante guardado correctamente");
            return "redirect:/listarEstudiantes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el estudiante: " + e.getMessage());
            model.addAttribute("titulo", "Registrar Estudiante");
            model.addAttribute("instituciones", institucionService.findAll());
            return "registrarEstudiantes";
        }
    }

    @GetMapping("/editarEstudiante/{id}")
    public String editarUsuario(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Estudiantes estudiante = estudianteService.findById(id);
        if (estudiante == null) {
            flash.addFlashAttribute("error", "Estudiante no encontrado");
            return "redirect:/listarEstudiantes";
        }

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("titulo", "Editar Estudiante");
        model.addAttribute("instituciones", institucionService.findAll());
        return "registrarEstudiantes";
    }

    @GetMapping("/eliminarEstudiante/{id}")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes flash) {
        try {
        	estudianteService.delete(id);
            flash.addFlashAttribute("success", "Estudiante eliminado correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al eliminar el estudiante: " + e.getMessage());
        }
        return "redirect:/listarEstudiantes";
    }
}
