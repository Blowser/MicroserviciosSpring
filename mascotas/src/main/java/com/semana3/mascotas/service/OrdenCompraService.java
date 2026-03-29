package com.semana3.mascotas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.semana3.mascotas.dto.OrdenCompraDTO;
import com.semana3.mascotas.dto.ProductoMascotaDTO;

@Service
public class OrdenCompraService {

    private final List<ProductoMascotaDTO> productos = new ArrayList<>();
    private final List<OrdenCompraDTO> ordenes = new ArrayList<>();

    public OrdenCompraService() {

        // Productos permanentes
        productos.add(new ProductoMascotaDTO("1", "Shampoo para perro", "Higiene", 5990));
        productos.add(new ProductoMascotaDTO("2", "Arena para gato", "Higiene", 7990));
        productos.add(new ProductoMascotaDTO("3", "Juguete mordedor", "Juguetes", 4990));
        productos.add(new ProductoMascotaDTO("4", "Collar antipulgas", "Salud", 9990));
        productos.add(new ProductoMascotaDTO("5", "Comida premium", "Alimento", 12990));

        // Órdenes iniciales
        ordenes.add(new OrdenCompraDTO("100", "1", "2026-03-20", "2", "CREADA"));
        ordenes.add(new OrdenCompraDTO("101", "3", "2026-03-21", "1", "CREADA"));
        ordenes.add(new OrdenCompraDTO("102", "5", "2026-03-22", "4", "CREADA"));
    }

    public List<OrdenCompraDTO> obtenerTodas() {
        return ordenes;
    }

    public OrdenCompraDTO obtenerPorId(String id) {
        return ordenes.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public OrdenCompraDTO crear(OrdenCompraDTO request) {

        OrdenCompraDTO nueva = OrdenCompraDTO.builder()
                .id(UUID.randomUUID().toString())
                .productoId(request.getProductoId())
                .fecha(request.getFecha())
                .cantidad(request.getCantidad())
                .estado("CREADA")
                .build();

        ordenes.add(nueva);
        return nueva;
    }

    public String obtenerEstado(String id) {
        OrdenCompraDTO orden = obtenerPorId(id);
        return orden != null ? orden.getEstado() : null;
    }
}
