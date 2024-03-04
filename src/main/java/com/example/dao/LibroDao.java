package com.example.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Autor;
import com.example.entities.Libro;


public interface LibroDao extends JpaRepository<Libro, Integer> {

    // Método para encontrar un libro por su ID
    Libro findById(int id);

    // Método para encontrar libros por el ID de un autor
    List<Libro> findByIdAutores(int id);

    // Método para encontrar libros por el ID de un autor y una fecha específica
    List<Libro> findByAutoresIdAndDate(Autor autor, LocalDate date);

}
