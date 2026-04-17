package com.example.bdmovies.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bdmovies.entity.BdmoviesEntity;

@Repository
public interface BdmoviesRepository extends JpaRepository<BdmoviesEntity, Long> {

}
