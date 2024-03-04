package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

}
