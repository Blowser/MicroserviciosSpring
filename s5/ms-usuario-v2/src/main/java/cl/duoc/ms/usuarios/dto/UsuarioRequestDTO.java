package cl.duoc.ms.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
	private String nombre;

	@NotBlank(message = "El email no puede estar vacío")
	@Email(message = "El email debe ser válido")
	private String email;

	@Min(value = 0, message = "La edad no puede ser negativa")
	@Max(value = 120, message = "La edad no puede ser mayor a 120")
	private Integer edad;
}
