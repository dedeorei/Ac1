package com.example.demo.repository;

import com.example.demo.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {

    List<Diretor> findByNomeStartingWith(String nome);

    @Query("SELECT d FROM Diretor d LEFT JOIN FETCH d.filmes WHERE d.id = :id")
    Optional<Diretor> findByIdWithFilmes(Long id);
}
