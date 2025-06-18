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
import com.ista.springboot.form.app.services.IInstitucionService;

import jakarta.validation.Valid;

@Controller
public class InstitucionControllers {

	@Autowired
	private IInstitucionService institucionService;

	@GetMapping({ "/", "/menu/login" })
	public String index() {
		return "menu/login";
	}

	@GetMapping("/menu/mmenuadmin")
	public String mostrarLayout() {
	    return "menu/layout"; 
	}
	
	@GetMapping("/menu/menudocente")
	public String mostrarLayout2() {
	    return "menu/layout2"; 
	}


	@GetMapping("/listarinstitucionesAdmin")
	public String listarInstituciones(Model model) {
		List<Institucion> instituciones = institucionService.findAll();
		model.addAttribute("titulo", "Lista de Instituciones");
		model.addAttribute("instituciones", instituciones);
		return "listarinstitucionesAdmin";
	}

	@GetMapping("/institucion")
	public String crearInstitucion(Model model) {
		model.addAttribute("institucion", new Institucion());
		model.addAttribute("titulo", "Registrar Institución");
		return "institucion";
	}

	@PostMapping("/guardarInstitucion")
	public String guardarInstitucion(@Valid @ModelAttribute("institucion") Institucion institucion,
			BindingResult result, Model model, RedirectAttributes flash) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Registrar Institución");
			return "institucion";
		}

		try {
			institucionService.save(institucion);
			flash.addFlashAttribute("success", "Institución guardada correctamente");
			return "redirect:/listarinstitucionesAdmin";
		} catch (Exception e) {
			model.addAttribute("error", "Error al guardar la institución: " + e.getMessage());
			model.addAttribute("titulo", "Registrar Institución");
			return "institucion";
		}
	}

	@GetMapping("/editarInstitucion/{id}")
	public String editarInstitucion(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Institucion institucion = institucionService.findById(id);
		if (institucion == null) {
			flash.addFlashAttribute("error", "Institución no encontrada");
			return "redirect:/listarinstitucionesAdmin";
		}
		model.addAttribute("institucion", institucion);
		model.addAttribute("titulo", "Editar Institución");
		return "institucion";
	}

	@GetMapping("/eliminarInstitucion/{id}")
	public String eliminarInstitucion(@PathVariable Long id, RedirectAttributes flash) {
		try {
			institucionService.delete(id);
			flash.addFlashAttribute("success", "Institución eliminada correctamente");
		} catch (Exception e) {
			flash.addFlashAttribute("error", "Error al eliminar la institución: " + e.getMessage());
		}
		return "redirect:/listarinstitucionesAdmin";
	}
}
