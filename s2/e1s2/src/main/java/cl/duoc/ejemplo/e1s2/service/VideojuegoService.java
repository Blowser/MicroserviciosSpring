package cl.duoc.ejemplo.e1s2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.ejemplo.e1s2.dto.VideojuegoDTO;

@Service
public class VideojuegoService {

	private List<VideojuegoDTO> videojuegos = new ArrayList<>();

	public VideojuegoService() {

		videojuegos.add(new VideojuegoDTO("1", "Minecraft", "PC", "Sandbox"));
		videojuegos.add(new VideojuegoDTO("2", "FIFA 24", "PS5", "Deportes"));
		videojuegos.add(new VideojuegoDTO("3", "Mario Kart", "Switch", "Carreras"));
	}

	public List<VideojuegoDTO> obtenerTodos() {

		return videojuegos;
	}

	public VideojuegoDTO obtenerPorId(String id) {

		for (VideojuegoDTO videojuego : videojuegos) {

			if (videojuego.getId().equals(id)) {

				return videojuego;
			}
		}
		return null;
	}

	public VideojuegoDTO crear(VideojuegoDTO nuevo) {

		videojuegos.add(nuevo);
		return nuevo;
	}
}
