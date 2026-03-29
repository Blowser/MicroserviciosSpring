package com.semana3.mascotas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.semana3.mascotas.dto.ProductoMascotaDTO;
import com.semana3.mascotas.service.OrdenCompraService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final OrdenCompraService service;

    public ProductoController(OrdenCompraService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductoMascotaDTO>> readAll() {
        return ResponseEntity.ok(service.obtenerProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable("id") String id) {
        ProductoMascotaDTO producto = service.obtenerProductoPorId(id);

        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<ProductoMascotaDTO> create(@RequestBody ProductoMascotaDTO body) {
        return ResponseEntity.ok(service.crearProducto(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody ProductoMascotaDTO body) {

        ProductoMascotaDTO actualizado = service.actualizarProducto(id, body);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {

        boolean eliminado = service.eliminarProducto(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
