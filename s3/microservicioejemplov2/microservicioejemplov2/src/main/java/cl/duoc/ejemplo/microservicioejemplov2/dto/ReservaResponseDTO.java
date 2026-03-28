package cl.duoc.ejemplo.microservicioejemplov2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponseDTO {

	private String id;
	private String nombreEstudiante;
	private String sala;
	private String fecha;
	private String hora;
	private String motivo;
	private String estado;
}
