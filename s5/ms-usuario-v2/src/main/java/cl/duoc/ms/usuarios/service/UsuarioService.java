package cl.duoc.ms.usuarios.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.ms.usuarios.dto.UsuarioRequestDTO;
import cl.duoc.ms.usuarios.dto.UsuarioResponseDTO;
import cl.duoc.ms.usuarios.entity.UsuarioEntity;
import cl.duoc.ms.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {

		this.usuarioRepository = usuarioRepository;
	}

	public List<UsuarioResponseDTO> obtenerTodos() {

		return usuarioRepository.findAll().stream().map(this::toDTO).toList();
	}

	public UsuarioResponseDTO obtenerPorId(Long id) {

		Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
		return usuario.map(this::toDTO).orElse(null);
	}

	public List<UsuarioResponseDTO> buscarPorNombre(String nombre) {

		return usuarioRepository.findByNombre(nombre).stream().map(this::toDTO).toList();
	}

	public List<UsuarioResponseDTO> buscarPorNombreParcial(String nombre) {

		return usuarioRepository.findByNombreContaining(nombre).stream().map(this::toDTO).toList();
	}

	public UsuarioResponseDTO buscarPorEmail(String email) {

		return usuarioRepository.findByEmail(email).map(this::toDTO).orElse(null);
	}

	public List<UsuarioResponseDTO> buscarPorEdadMayorA(Integer edad) {

		return usuarioRepository.findByEdadGreaterThan(edad).stream().map(this::toDTO).toList();
	}

	public List<UsuarioResponseDTO> buscarPorRangoEdad(Integer min, Integer max) {

		return usuarioRepository.findByEdadBetween(min, max).stream().map(this::toDTO).toList();
	}

	public List<UsuarioResponseDTO> obtenerActivos() {

		return usuarioRepository.findByActivo(1).stream().map(this::toDTO).toList();
	}

	public List<UsuarioResponseDTO> obtenerActivosOrdenados() {

		return usuarioRepository.findByActivoOrderByNombreAsc(1).stream().map(this::toDTO).toList();
	}

	public List<UsuarioResponseDTO> buscarActivosPorEdadMayorA(Integer edad) {

		return usuarioRepository.findByActivoAndEdadGreaterThan(1, edad).stream().map(this::toDTO).toList();
	}

	public List<UsuarioResponseDTO> buscarPorFechaCreacion(LocalDateTime fecha) {

		return usuarioRepository.findByFechaCreacionAfter(fecha).stream().map(this::toDTO).toList();
	}

	public List<UsuarioResponseDTO> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {

		return usuarioRepository.findByFechaCreacionBetween(inicio, fin).stream().map(this::toDTO).toList();
	}

	public List<UsuarioResponseDTO> buscarComplejo(String nombre) {

		return usuarioRepository.findByNombreContainingAndActivoOrderByEdadDesc(nombre, 1).stream().map(this::toDTO)
				.toList();
	}

	public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO usuarioRequest) {

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNombre(usuarioRequest.getNombre());
		usuario.setEmail(usuarioRequest.getEmail());
		usuario.setEdad(usuarioRequest.getEdad());
		usuario.setActivo(1);

		UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);
		return toDTO(usuarioGuardado);
	}

	public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioRequest) {

		Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isEmpty()) {

			return null;
		}

		UsuarioEntity usuario = usuarioOptional.get();
		usuario.setNombre(usuarioRequest.getNombre());
		usuario.setEmail(usuarioRequest.getEmail());
		usuario.setEdad(usuarioRequest.getEdad());

		UsuarioEntity usuarioActualizado = usuarioRepository.save(usuario);
		return toDTO(usuarioActualizado);
	}

	public boolean eliminarUsuario(Long id) {

		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return true;
		}

		return false;
	}

	public boolean eliminarUsuarioLogica(Long id) {

		Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isEmpty()) {

			return false;
		}

		UsuarioEntity usuario = usuarioOptional.get();
		usuario.setActivo(0);
		usuarioRepository.save(usuario);

		return true;
	}

	private UsuarioResponseDTO toDTO(UsuarioEntity usuario) {

		return new UsuarioResponseDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getEdad());
	}
}