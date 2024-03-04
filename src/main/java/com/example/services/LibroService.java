package com.example.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.entities.Libro;

public interface LibroService {

    public List<Libro> findById(int idAutor);
    public List<Libro> findLibrosByAutorId(int idAutor);

    public List<Libro> findAll();
    public Libro save(Libro libro);
    Libro findFirstById(int id);
    public boolean existById(Integer libroId);
    Page<Libro> findAll(Pageable pageable);
    public void deleteById(Integer id);

}
