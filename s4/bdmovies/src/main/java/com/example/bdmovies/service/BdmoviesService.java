package com.example.bdmovies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bdmovies.dto.BdmoviesRequestDTO;
import com.example.bdmovies.dto.BdmoviesResponseDTO;
import com.example.bdmovies.entity.BdmoviesEntity;
import com.example.bdmovies.repository.BdmoviesRepository;

@Service
public class BdmoviesService {

    private final BdmoviesRepository repository;

    public BdmoviesService(BdmoviesRepository repository) {
        this.repository = repository;
    }

    public List<BdmoviesResponseDTO> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public BdmoviesResponseDTO obtenerPorId(Long id) {
        Optional<BdmoviesEntity> Bdmovies = repository.findById(id);
        return Bdmovies.map(this::toDTO).orElse(null);
    }

    public BdmoviesResponseDTO crear(BdmoviesRequestDTO dto) {
        BdmoviesEntity entity = new BdmoviesEntity(
                null,
                dto.getTitulo(),
                dto.getDirector(),
                dto.getGenero()
        );
        return toDTO(repository.save(entity));
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    public BdmoviesResponseDTO actualizar(Long id, BdmoviesRequestDTO dto) {
        Optional<BdmoviesEntity> Bdmovies = repository.findById(id);

        if (Bdmovies.isEmpty()) return null;

        BdmoviesEntity entity = Bdmovies.get();
        entity.setTitulo(dto.getTitulo());
        entity.setDirector(dto.getDirector());
        entity.setGenero(dto.getGenero());

        return toDTO(repository.save(entity));
    }

    private BdmoviesResponseDTO toDTO(BdmoviesEntity e) {
        return new BdmoviesResponseDTO(
                e.getId(),
                e.getTitulo(),
                e.getDirector(),
                e.getGenero()
        );
    }
}
