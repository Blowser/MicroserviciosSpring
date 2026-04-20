package com.example.bdmovies.repository;

import com.example.bdmovies.entity.BdmoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BdmoviesRepository extends JpaRepository<BdmoviesEntity, Long> {

    List<BdmoviesEntity> findByTitulo(String titulo);

    List<BdmoviesEntity> findByTituloContaining(String titulo);

    List<BdmoviesEntity> findByGenero(String genero);

    List<BdmoviesEntity> findByDirectorContaining(String director);

    // NUEVO: buscar por rango de rating
    List<BdmoviesEntity> findByRatingBetween(Double min, Double max);

    // NUEVO: solo activas
    List<BdmoviesEntity> findByActivo(Integer activo);
}

