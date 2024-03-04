package com.example.services;

import java.util.List;

import com.example.entities.Libro;

public interface LibroService {

    public List<Libro> findById(int idAutor);
    public List<Libro> findLibrosByAutorId(int idAutor);

    public List<Libro> findAll();

}
