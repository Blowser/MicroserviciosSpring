package cl.duoc.ms.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

	private UsuarioResponseDTO toDTO(UsuarioEntity usuario) {

		return new UsuarioResponseDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail());
	}
}
