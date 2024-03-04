package com.example.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Libro;
import com.example.entities.Autor;


public interface LibroDao extends JpaRepository<Libro, Integer> {

    List<Libro> findByAutores(Autor autor);

    List<Libro> findById(int id);

    List<Libro> findLibrosByAutoresId(Integer autorId);

    List<Libro> findAllLibrosPageable(Integer id, Sort sort);

}
