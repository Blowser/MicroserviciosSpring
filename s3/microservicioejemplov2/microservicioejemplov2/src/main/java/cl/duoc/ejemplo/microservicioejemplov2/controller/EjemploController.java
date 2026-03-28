package cl.duoc.ejemplo.microservicioejemplov2.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/microservicio")
public class EjemploController {

	@PostMapping
	public ResponseEntity<String> create(@RequestBody Map<String, String> body) {
		return ResponseEntity.ok("Integración OK - POST, recibido body: " + body);
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> read(@PathVariable("id") String id) {
		return ResponseEntity.ok("Integración OK - GET, recibido path variable: " + id);
	}

	@PutMapping
	public ResponseEntity<String> update(@RequestParam("status") String status) {
		return ResponseEntity.ok("Integración OK - PUT, recibido query param: " + status);
	}

	@DeleteMapping
	public ResponseEntity<String> delete(@RequestHeader("Authorization") String authHeader) {
		return ResponseEntity.ok("Integración OK - DELETE, recibido header: " + authHeader);
	}
}
