package com.example.bdmovies.service;

import com.example.bdmovies.dto.BdmoviesRequestDTO;
import com.example.bdmovies.dto.BdmoviesResponseDTO;
import com.example.bdmovies.entity.BdmoviesEntity;
import com.example.bdmovies.repository.BdmoviesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BdmoviesService {

    private final BdmoviesRepository repository;

    public BdmoviesService(BdmoviesRepository repository) {
        this.repository = repository;
    }

    // ============================
    // CRUD BÁSICO
    // ============================

    public List<BdmoviesResponseDTO> obtenerTodos() {
        return repository.findByActivo(1)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public BdmoviesResponseDTO obtenerPorId(Long id) {
        Optional<BdmoviesEntity> opt = repository.findById(id);
        if (opt.isEmpty() || opt.get().getActivo() == 0) return null;
        return toDTO(opt.get());
    }

    public BdmoviesResponseDTO crear(BdmoviesRequestDTO dto) {
        BdmoviesEntity entity = new BdmoviesEntity();
        entity.setTitulo(dto.getTitulo());
        entity.setDirector(dto.getDirector());
        entity.setGenero(dto.getGenero());
        entity.setRating(dto.getRating());
        entity.setActivo(1);

        return toDTO(repository.save(entity));
    }

    public BdmoviesResponseDTO actualizar(Long id, BdmoviesRequestDTO dto) {
        Optional<BdmoviesEntity> opt = repository.findById(id);
        if (opt.isEmpty()) return null;

        BdmoviesEntity entity = opt.get();
        entity.setTitulo(dto.getTitulo());
        entity.setDirector(dto.getDirector());
        entity.setGenero(dto.getGenero());
        entity.setRating(dto.getRating());

        return toDTO(repository.save(entity));
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    // ============================
    // SOFT DELETE
    // ============================

    public boolean desactivar(Long id) {
        Optional<BdmoviesEntity> opt = repository.findById(id);
        if (opt.isEmpty()) return false;

        BdmoviesEntity entity = opt.get();
        entity.setActivo(0);
        repository.save(entity);
        return true;
    }

    // ============================
    // BÚSQUEDAS AVANZADAS
    // ============================

    public List<BdmoviesResponseDTO> buscarPorTitulo(String titulo) {
        return repository.findByTitulo(titulo)
                .stream()
                .filter(e -> e.getActivo() == 1)
                .map(this::toDTO)
                .toList();
    }

    public List<BdmoviesResponseDTO> buscarPorTituloParcial(String titulo) {
        return repository.findByTituloContaining(titulo)
                .stream()
                .filter(e -> e.getActivo() == 1)
                .map(this::toDTO)
                .toList();
    }

    public List<BdmoviesResponseDTO> buscarPorGenero(String genero) {
        return repository.findByGenero(genero)
                .stream()
                .filter(e -> e.getActivo() == 1)
                .map(this::toDTO)
                .toList();
    }

    public List<BdmoviesResponseDTO> buscarPorDirectorParcial(String director) {
        return repository.findByDirectorContaining(director)
                .stream()
                .filter(e -> e.getActivo() == 1)
                .map(this::toDTO)
                .toList();
    }

    public List<BdmoviesResponseDTO> buscarPorRating(Double min, Double max) {
        return repository.findByRatingBetween(min, max)
                .stream()
                .filter(e -> e.getActivo() == 1)
                .map(this::toDTO)
                .toList();
    }

    // ============================
    // CONVERSIÓN DTO
    // ============================

    private BdmoviesResponseDTO toDTO(BdmoviesEntity e) {
        return new BdmoviesResponseDTO(
                e.getId(),
                e.getTitulo(),
                e.getDirector(),
                e.getGenero(),
                e.getRating()
        );
    }
}
