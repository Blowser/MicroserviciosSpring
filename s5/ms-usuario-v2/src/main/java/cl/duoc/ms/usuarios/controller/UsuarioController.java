package cl.duoc.ms.usuarios.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms.usuarios.dto.UsuarioRequestDTO;
import cl.duoc.ms.usuarios.dto.UsuarioResponseDTO;
import cl.duoc.ms.usuarios.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {

		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos() {

		return ResponseEntity.ok(usuarioService.obtenerTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {

		UsuarioResponseDTO usuario = usuarioService.obtenerPorId(id);

		if (usuario == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);
	}

	// Buscar por nombre exacto
	@GetMapping("/buscar")
	public ResponseEntity<List<UsuarioResponseDTO>> buscarPorNombre(@RequestParam String nombre) {

		return ResponseEntity.ok(usuarioService.buscarPorNombre(nombre));
	}

	// Buscar por nombre parcial
	@GetMapping("/buscar-parcial")
	public ResponseEntity<List<UsuarioResponseDTO>> buscarPorNombreParcial(@RequestParam String nombre) {

		return ResponseEntity.ok(usuarioService.buscarPorNombreParcial(nombre));
	}

	// Buscar por email
	@GetMapping("/email")
	public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {

		UsuarioResponseDTO usuario = usuarioService.buscarPorEmail(email);

		if (usuario == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);
	}

	// Buscar por edad mayor a
	@GetMapping("/edad-mayor")
	public ResponseEntity<List<UsuarioResponseDTO>> buscarPorEdadMayor(@RequestParam Integer edad) {

		return ResponseEntity.ok(usuarioService.buscarPorEdadMayorA(edad));
	}

	// Buscar por rango de edad
	@GetMapping("/edad-rango")
	public ResponseEntity<List<UsuarioResponseDTO>> buscarPorRangoEdad(@RequestParam Integer min,
			@RequestParam Integer max) {

		return ResponseEntity.ok(usuarioService.buscarPorRangoEdad(min, max));
	}

	// Obtener usuarios activos
	@GetMapping("/activos")
	public ResponseEntity<List<UsuarioResponseDTO>> obtenerActivos() {

		return ResponseEntity.ok(usuarioService.obtenerActivos());
	}

	// Activos ordenados por nombre
	@GetMapping("/activos-ordenados")
	public ResponseEntity<List<UsuarioResponseDTO>> obtenerActivosOrdenados() {

		return ResponseEntity.ok(usuarioService.obtenerActivosOrdenados());
	}

	// Activos con edad mayor a
	@GetMapping("/activos-edad")
	public ResponseEntity<List<UsuarioResponseDTO>> buscarActivosPorEdad(@RequestParam Integer edad) {

		return ResponseEntity.ok(usuarioService.buscarActivosPorEdadMayorA(edad));
	}

	// Buscar por fecha (después de)
	@GetMapping("/fecha")
	public ResponseEntity<List<UsuarioResponseDTO>>
			buscarPorFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {

		return ResponseEntity.ok(usuarioService.buscarPorFechaCreacion(fecha));
	}

	// Buscar entre fechas
	@GetMapping("/fecha-rango")
	public ResponseEntity<List<UsuarioResponseDTO>> buscarPorRangoFechas(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {

		return ResponseEntity.ok(usuarioService.buscarPorRangoFechas(inicio, fin));
	}

	// Ejemplo complejo
	@GetMapping("/buscar-complejo")
	public ResponseEntity<List<UsuarioResponseDTO>> buscarComplejo(@RequestParam String nombre) {

		return ResponseEntity.ok(usuarioService.buscarComplejo(nombre));
	}

	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequest) {

		UsuarioResponseDTO usuarioCreado = usuarioService.crearUsuario(usuarioRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable Long id,
			@Valid @RequestBody UsuarioRequestDTO usuarioRequest) {

		UsuarioResponseDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioRequest);

		if (usuarioActualizado == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuarioActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {

		boolean eliminado = usuarioService.eliminarUsuario(id);

		if (!eliminado) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/desactivar")
	public ResponseEntity<?> desactivarUsuario(@PathVariable Long id) {

		boolean desactivado = usuarioService.eliminarUsuarioLogica(id);

		if (!desactivado) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}
}
