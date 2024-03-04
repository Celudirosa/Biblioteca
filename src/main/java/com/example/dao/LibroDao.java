package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Libro;

public interface LibroDao extends JpaRepository<Libro, Integer> {

    List<Libro> findLibrosByAutoresId(Integer autorId);

    List<Libro> findByTitulo(String titulo);

}
