package com.example.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.entities.Autor;
import com.example.entities.Libro;

public interface LibroService {

    List<Libro> findAll();
    Page<Libro> findAll(Pageable pageable);
    Page<Libro> findAll(Sort sort);

    Libro findById(int id);
    List<Libro> findLibrosByAutorId(int id);

    Libro save(Libro libro);
    void delete(Libro libro);
    List<Libro> findByAutorAndDate(Autor autor, LocalDate date);

}
