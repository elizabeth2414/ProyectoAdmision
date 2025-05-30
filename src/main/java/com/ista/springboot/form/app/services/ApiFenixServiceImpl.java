package com.ista.springboot.form.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ista.springboot.form.app.dao.IRolDao;
import com.ista.springboot.form.app.dao.IUsuarioDao;
import com.ista.springboot.form.app.entity.Rol;
import com.ista.springboot.form.app.entity.UsuarioRol;
import com.ista.springboot.form.app.entity.Usuarios;
import com.ista.springboot.form.app.entity.Rol.NombreRol;

@Service
public class ApiFenixServiceImpl implements IApiFenixService {

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IRolDao rolDao;

	private static final String API_URL = "http://apps.tecazuay.edu.ec:9093/aseguramiento/api/fenix/cedula/";

	@Override
	public Usuarios buscarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		return usuarioDao.findByCedula(cedula).orElse(null);
	}

	@Override
	public List<Usuarios> buscarPorNombreOApellido(String filtro) {
		// TODO Auto-generated method stub
		return usuarioDao.findByPrimerNombreContainingIgnoreCaseOrPrimerApellidoContainingIgnoreCase(filtro, filtro);
	}

	@Override
	public Usuarios buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public Usuarios obtenerORegistrarUsuarioPorCedula(String cedula) {
		// TODO Auto-generated method stub
		// 1. Buscar localmente
	    Optional<Usuarios> usuarioOpt = usuarioDao.findByCedula(cedula);
	    if (usuarioOpt.isPresent()) {
	        return usuarioOpt.get();
	    }

	    // 2. Consultar API externa
	    RestTemplate restTemplate = new RestTemplate();
	    Usuarios resultado = restTemplate.getForObject(API_URL + cedula, Usuarios.class);

	    if (resultado != null) {
	        Usuarios apiDto = resultado;

	        Usuarios nuevoUsuario = new Usuarios();
	        nuevoUsuario.setCedula(apiDto.getCedula());
	        nuevoUsuario.setPrimerNombre(apiDto.getPrimerNombre());
	        nuevoUsuario.setSegundoNombre(apiDto.getSegundoNombre());
	        nuevoUsuario.setPrimerApellido(apiDto.getPrimerApellido());
	        nuevoUsuario.setSegundoApellido(apiDto.getSegundoApellido());
	        nuevoUsuario.setCorreo(apiDto.getCorreo() != null ? apiDto.getCorreo() : "");
	        nuevoUsuario.setCelular(apiDto.getCelular());

	        Rol rolDocente = rolDao.findByNombre(NombreRol.DOCENTE)
	                .orElseThrow(() -> new RuntimeException("Rol DOCENTE no existe"));

	        UsuarioRol usuarioRol = new UsuarioRol();
	        usuarioRol.setUsuario(nuevoUsuario); // Asegúrate de asociar el usuario
	        usuarioRol.setRol(rolDocente);

	        nuevoUsuario.getRoles().add(usuarioRol);

	        return usuarioDao.save(nuevoUsuario);
	    } else {
	        throw new RuntimeException("Usuario no encontrado en API externa");
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuarios>)usuarioDao.findAll();
	}

	@Override
	@Transactional
	public Usuarios save(Usuarios usuarios) {
		// TODO Auto-generated method stub
		return usuarioDao.save(usuarios);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuarios findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(id);
	}
}
