package com.ista.springboot.form.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ista.springboot.form.app.entity.Rol;
import com.ista.springboot.form.app.entity.Usuarios;

public interface IUsuarioDao extends CrudRepository<Usuarios, Long> {

	Optional<Usuarios> findByCedula(String cedula);

	List<Usuarios> findByPrimerNombreContainingIgnoreCaseOrPrimerApellidoContainingIgnoreCase(String nombre,
			String apellido);

	@Query("SELECT u FROM Usuarios u JOIN u.roles ur JOIN ur.rol r WHERE r.nombre = :nombreRol")
	List<Usuarios> findAllByRolesNombre(@Param("nombreRol") Rol.NombreRol nombreRol);

	@Query(value = "SELECT * FROM usuario u " + "LEFT JOIN usuarioroles ur ON u.id_usuariosapi = ur.usuario "
			+ "WHERE ur.usuario IS NULL " + "AND (LOWER(u.cedula) LIKE LOWER(CONCAT('%', :filtro, '%')) "
			+ "OR LOWER(u.primer_nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) "
			+ "OR LOWER(u.primer_apellido) LIKE LOWER(CONCAT('%', :filtro, '%')))", nativeQuery = true)
	List<Usuarios> buscarUsuariosSinRolConFiltro(@Param("filtro") String filtro);
}
