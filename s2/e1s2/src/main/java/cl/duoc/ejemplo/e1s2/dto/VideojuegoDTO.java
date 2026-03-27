package cl.duoc.ejemplo.e1s2.dto;

public class VideojuegoDTO {

	private String id;
	private String nombre;
	private String plataforma;
	private String genero;

	public VideojuegoDTO() {
	}

	public VideojuegoDTO(String id, String nombre, String plataforma, String genero) {
		this.id = id;
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.genero = genero;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
