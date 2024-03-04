package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    List<Autor> findAutoresByLibrosId(Integer libroId);
}
