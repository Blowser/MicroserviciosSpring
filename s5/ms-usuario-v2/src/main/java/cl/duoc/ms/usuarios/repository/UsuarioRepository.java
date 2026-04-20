package cl.duoc.ms.usuarios.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.ms.usuarios.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	// Búsqueda por nombre exacto
	List<UsuarioEntity> findByNombre(String nombre);

	// Búsqueda por nombre parcial (LIKE %texto%)
	List<UsuarioEntity> findByNombreContaining(String nombre);

	// Búsqueda por email
	Optional<UsuarioEntity> findByEmail(String email);

	// Búsqueda por edad mayor a
	List<UsuarioEntity> findByEdadGreaterThan(Integer edad);

	// Búsqueda por rango de edad
	List<UsuarioEntity> findByEdadBetween(Integer min, Integer max);

	// Solo usuarios activos
	List<UsuarioEntity> findByActivo(Integer activo);

	// Usuarios activos ordenados por nombre
	List<UsuarioEntity> findByActivoOrderByNombreAsc(Integer activo);

	// Usuarios activos con edad mayor a X
	List<UsuarioEntity> findByActivoAndEdadGreaterThan(Integer activo, Integer edad);

	// Usuarios creados después de cierta fecha
	List<UsuarioEntity> findByFechaCreacionAfter(LocalDateTime fecha);

	// Usuarios creados entre fechas
	List<UsuarioEntity> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);

	// Ejemplo complejo
	List<UsuarioEntity> findByNombreContainingAndActivoOrderByEdadDesc(String nombre, Integer activo);
}
